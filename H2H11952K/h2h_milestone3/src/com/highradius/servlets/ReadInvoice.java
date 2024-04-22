package com.highradius.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;

import com.highradius.model.Invoice;

@WebServlet("/ReadInvoice")
public class ReadInvoice extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InvoiceDao invoiceDAO;

    public void init() {
        invoiceDAO = new InvoiceDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse;
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            List<Invoice> invoiceList = invoiceDAO.getInvoice();
            Gson gson = new Gson();
            jsonResponse = gson.toJson(invoiceList);
            out.write(jsonResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse = "{\"error\": \"" + e.getMessage() + "\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}




