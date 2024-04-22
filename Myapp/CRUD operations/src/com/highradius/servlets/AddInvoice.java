package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;

@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDao invoiceDAO;

    public void init() {
        invoiceDAO = new InvoiceDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int slNo = Integer.parseInt(request.getParameter("slNo"));
        String customerOrderId = request.getParameter("customerOrderId");
        String salesOrg = request.getParameter("salesOrg");
        String distributionChannel = request.getParameter("distributionChannel");
        String division = request.getParameter("division");
        String releasedCreditValue = request.getParameter("releasedCreditValue");
        String purchaseOrderType = request.getParameter("purchaseOrderType");
        String companyCode = request.getParameter("companyCode");
        String orderCreationDate = request.getParameter("orderCreationDate");
        String orderCreationTime = request.getParameter("orderCreationTime");
        String creditControlArea = request.getParameter("creditControlArea");
        String soldToParty = request.getParameter("soldToParty");
        String orderAmount = request.getParameter("orderAmount");
        String requestedDeliveryDate = request.getParameter("requestedDeliveryDate");
        String orderCurrency = request.getParameter("orderCurrency");
        String creditStatus = request.getParameter("creditStatus");
        String customerNumber = request.getParameter("customerNumber");
        String amountInUSD = request.getParameter("amountInUSD");
        String uniqueCustomerId = request.getParameter("uniqueCustomerId");

        Invoice newInvoice = new Invoice(slNo, customerOrderId, salesOrg, distributionChannel, division,
                releasedCreditValue, purchaseOrderType, companyCode, orderCreationDate, orderCreationTime,
                creditControlArea, soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus,
                customerNumber, amountInUSD, uniqueCustomerId);

        try {
            invoiceDAO.insertInvoice(newInvoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
