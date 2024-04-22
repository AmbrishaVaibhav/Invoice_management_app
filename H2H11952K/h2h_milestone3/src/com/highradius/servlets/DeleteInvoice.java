package com.highradius.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

@WebServlet("/DeleteInvoice")
public class DeleteInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDao invoiceDAO;

    public void init() {
        invoiceDAO = new InvoiceDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int slNo = Integer.parseInt(request.getParameter("slNo")); // Assuming slNo is the unique identifier for the invoice

        try {
            invoiceDAO.deleteInvoice(slNo); // Assuming you have a method to delete the invoice in the DAO
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
