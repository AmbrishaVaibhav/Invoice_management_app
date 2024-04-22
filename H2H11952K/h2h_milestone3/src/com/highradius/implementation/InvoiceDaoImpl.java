package com.highradius.implementation;

import com.highradius.connection.DatabaseConnection;
import com.highradius.model.Invoice;

import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
	private DatabaseConnection databaseConnection;

    public InvoiceDaoImpl() {
        databaseConnection = new DatabaseConnection();
    }

    @Override
    public List<Invoice> getInvoice() {
        // Fetch the invoice data from the database using the DatabaseConnection object
        databaseConnection.getInvoice();

        // Retrieve the fetched invoice data
        return databaseConnection.getInvoice();
    }

    public void insertInvoice(Invoice invoice) {
        // Implement insert logic
        databaseConnection.addInvoice(invoice);
    }

    public void updateInvoice(int id, Invoice invoice) {
        // Implement update logic
        databaseConnection.updateInvoice(id, invoice);
    }

    public void deleteInvoice(int id) {
        // Implement delete logic
        databaseConnection.deleteInvoice(id);
    }
}
