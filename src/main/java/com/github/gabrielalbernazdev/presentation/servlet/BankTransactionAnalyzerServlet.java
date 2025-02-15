package com.github.gabrielalbernazdev.presentation.servlet;

import com.github.gabrielalbernazdev.application.service.BankTransactionService;
import com.github.gabrielalbernazdev.application.service.impl.BankTransactionServiceImpl;
import com.github.gabrielalbernazdev.domain.model.BankTransaction;
import com.github.gabrielalbernazdev.util.constants.PathConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.List;

@WebServlet(PathConstants.BANK_TRANSACTION_INDEX_PATH)
@MultipartConfig
public class BankTransactionAnalyzerServlet extends HttpServlet {
    private BankTransactionService service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new BankTransactionServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PathConstants.APP_INDEX_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Part requestPartFile = req.getPart("file");
        List<BankTransaction> transactions =  service.processBankTransactionsFile(requestPartFile);

        req.setAttribute("transactions", transactions);
        getServletContext().getRequestDispatcher(PathConstants.APP_INDEX_PATH).forward(req, resp);
    }
}
