package com.highradius.connection;

import com.highradius.model.Invoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hrc_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ambrisha";

    private List<Invoice> invoiceList;
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public DatabaseConnection() {
        invoiceList = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        String insertSQL = "INSERT INTO h2h_oap " +
                "(CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, " +
                "PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, " +
                "SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, " +
                "AMOUNT_IN_USD, UNIQUE_CUST_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        	PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, invoice.getCustomerOrderId());
            statement.setString(2, invoice.getSalesOrg());
            statement.setString(3, invoice.getDistributionChannel());
            statement.setString(4, invoice.getDivision());
            statement.setDouble(5, Double.parseDouble(invoice.getReleasedCreditValue()));
            statement.setString(6, invoice.getPurchaseOrderType());
            statement.setInt(7, Integer.parseInt(invoice.getCompanyCode()));
            statement.setString(8, invoice.getOrderCreationDate());
            statement.setString(9, invoice.getOrderCreationTime());
            statement.setString(10, invoice.getCreditControlArea());
            statement.setInt(11, Integer.parseInt(invoice.getSoldToParty()));
            statement.setDouble(12, Double.parseDouble(invoice.getOrderAmount()));
            statement.setString(13, invoice.getRequestedDeliveryDate());
            statement.setString(14, invoice.getOrderCurrency());
            statement.setString(15, invoice.getCreditStatus());
            statement.setInt(16, Integer.parseInt(invoice.getCustomerNumber()));
            statement.setDouble(17, Double.parseDouble(invoice.getAmountInUSD()));
            statement.setLong(18, Long.parseLong(invoice.getUniqueCustomerId()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Invoice added successfully.");
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteInvoice(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Creating a statement object
            statement = connection.createStatement();

            // Deleting the invoice with the given ID
            String sql = "DELETE FROM h2h_oap WHERE Sl_no = " + id;
            statement.executeUpdate(sql);

            // Removing the deleted invoice from the invoice list
            invoiceList.removeIf(invoice -> invoice.getSlNo() == id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources in the finally block
            try {
                if (statement != null)
                    statement.close();

                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Invoice> getInvoice() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing a connection to the database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Creating a statement object
            statement = connection.createStatement();

            // Fetching records
            String sql = "SELECT * FROM h2h_oap LIMIT 100";
            resultSet = statement.executeQuery(sql);

            // Clearing the existing invoice list
            invoiceList.clear();

            // Populating the invoice list with data from the result set
            while (resultSet.next()) {
                int slNo = resultSet.getInt("Sl_no");
                String customerOrderId = resultSet.getString("CUSTOMER_ORDER_ID");
                String salesOrg = resultSet.getString("SALES_ORG");
                String distributionChannel = resultSet.getString("DISTRIBUTION_CHANNEL");
                String division = resultSet.getString("DIVISION");
                String releasedCreditValue = String.valueOf(resultSet.getDouble("RELEASED_CREDIT_VALUE"));
                String purchaseOrderType = resultSet.getString("PURCHASE_ORDER_TYPE");
                String companyCode = String.valueOf(resultSet.getInt("COMPANY_CODE"));
                String orderCreationDate = resultSet.getString("ORDER_CREATION_DATE");
                String orderCreationTime = resultSet.getString("ORDER_CREATION_TIME");
                String creditControlArea = resultSet.getString("CREDIT_CONTROL_AREA");
                String soldToParty = String.valueOf(resultSet.getInt("SOLD_TO_PARTY"));
                String orderAmount = String.valueOf(resultSet.getDouble("ORDER_AMOUNT"));
                String requestedDeliveryDate = resultSet.getString("REQUESTED_DELIVERY_DATE");
                String orderCurrency = resultSet.getString("ORDER_CURRENCY");
                String creditStatus = resultSet.getString("CREDIT_STATUS");
                String customerNumber = String.valueOf(resultSet.getInt("CUSTOMER_NUMBER"));
                String amountInUSD = String.valueOf(resultSet.getDouble("AMOUNT_IN_USD"));
                String uniqueCustId = String.valueOf(resultSet.getLong("UNIQUE_CUST_ID"));

                // Creating an Invoice object and adding it to the list
                Invoice invoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division,
                        releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime,
                        creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency,
                        creditStatus, customerNumber, amountInUSD, uniqueCustId);
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources in the finally block
            try {
                if (resultSet != null)
                    resultSet.close();

                if (statement != null)
                    statement.close();

                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return invoiceList;
    }

    public void updateInvoice(int id, Invoice updatedInvoice) {
        for (Invoice invoice : invoiceList) {
            if (invoice.getSlNo() == id) {
                // Update the invoice with the new details
                invoice.setCustomerOrderId(updatedInvoice.getCustomerOrderId());
                invoice.setSalesOrg(updatedInvoice.getSalesOrg());
                invoice.setDistributionChannel(updatedInvoice.getDistributionChannel());
                invoice.setDivision(updatedInvoice.getDivision());
                invoice.setReleasedCreditValue(updatedInvoice.getReleasedCreditValue());
                invoice.setPurchaseOrderType(updatedInvoice.getPurchaseOrderType());
                invoice.setCompanyCode(updatedInvoice.getCompanyCode());
                invoice.setOrderCreationDate(updatedInvoice.getOrderCreationDate());
                invoice.setOrderCreationTime(updatedInvoice.getOrderCreationTime());
                invoice.setCreditControlArea(updatedInvoice.getCreditControlArea());
                invoice.setSoldToParty(updatedInvoice.getSoldToParty());
                invoice.setOrderAmount(updatedInvoice.getOrderAmount());
                invoice.setRequestedDeliveryDate(updatedInvoice.getRequestedDeliveryDate());
                invoice.setOrderCurrency(updatedInvoice.getOrderCurrency());
                invoice.setCreditStatus(updatedInvoice.getCreditStatus());
                invoice.setCustomerNumber(updatedInvoice.getCustomerNumber());
                invoice.setAmountInUSD(updatedInvoice.getAmountInUSD());
                invoice.setUniqueCustomerId(updatedInvoice.getUniqueCustomerId());
                
                break;
            }
        }
    }

    public static void main(String[] args) {
        DatabaseConnection databaseConnector = new DatabaseConnection();
        databaseConnector.getInvoice();

        List<Invoice> invoiceList = databaseConnector.getInvoice();
        System.out.println("Invoice Details:");
        // Printing the fetched invoice data
        for (Invoice invoice : invoiceList) {
            System.out.println("Sl No: " + invoice.getSlNo() + " Customer Order ID: " + invoice.getCustomerOrderId()
                    + " Sales Org: " + invoice.getSalesOrg());
            // Print other invoice details similarly
        }
        
     
    }
}