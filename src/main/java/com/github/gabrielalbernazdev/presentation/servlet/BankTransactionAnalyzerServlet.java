package com.github.gabrielalbernazdev.presentation.servlet;

import com.github.gabrielalbernazdev.application.service.BankTransactionService;
import com.github.gabrielalbernazdev.application.service.impl.BankTransactionServiceImpl;
import com.github.gabrielalbernazdev.domain.model.BankTransaction;
import com.github.gabrielalbernazdev.infra.exception.BankTransactionParserException;
import com.github.gabrielalbernazdev.util.constants.MessageConstants;
import com.github.gabrielalbernazdev.util.constants.PathConstants;
import com.github.gabrielalbernazdev.util.servlet.ServletUtil;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.sendRedirect(PathConstants.APP_INDEX_PATH);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final Part requestPartFile = req.getPart("file");

        if(requestPartFile == null || requestPartFile.getSize() == 0) {
            ServletUtil.forwardWithError(req, res, MessageConstants.BANK_TRANSACTION_ERROR_PART_FILE, PathConstants.APP_INDEX_PATH);
            return;
        }

        List<BankTransaction> transactions = null;

        try {
            transactions =  service.processBankTransactionsFile(requestPartFile);
        } catch (BankTransactionParserException e) {
            ServletUtil.forwardWithError(req, res, "Error to parse file: " + e.getMessage(), PathConstants.APP_INDEX_PATH);
        } catch (Exception e) {
            ServletUtil.forwardWithError(req, res, MessageConstants.GENERIC_ERROR, PathConstants.APP_INDEX_PATH);
        }

        req.setAttribute("transactions", transactions);
        ServletUtil.forward(req, res, PathConstants.APP_INDEX_PATH);
    }
}
