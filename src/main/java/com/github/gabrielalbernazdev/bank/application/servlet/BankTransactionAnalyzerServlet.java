package com.github.gabrielalbernazdev.bank.application.servlet;

import com.github.gabrielalbernazdev.bank.domain.model.BankTransaction;
import com.github.gabrielalbernazdev.bank.infra.extractor.FilePartExtractor;
import com.github.gabrielalbernazdev.bank.infra.parser.BankTransactionParser;
import com.github.gabrielalbernazdev.bank.infra.parser.BankTransactionParserFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bank/transactions")
@MultipartConfig
public class BankTransactionAnalyzerServlet extends HttpServlet {
    private final String INDEX_ROUTE = "/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(INDEX_ROUTE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Part requestPartFile = req.getPart("file");
        final BankTransactionParser parser = BankTransactionParserFactory.getBankTransactionParser(requestPartFile.getContentType());
        final List<String> fileLines = FilePartExtractor.extractFileLines(requestPartFile.getInputStream());
        List<BankTransaction> transactions =  parser.parse(fileLines);

        req.setAttribute("transactions", transactions);
        getServletContext().getRequestDispatcher(INDEX_ROUTE).forward(req, resp);
    }

    protected List<String> getFileLines(InputStream fileInputStream) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            return br.lines().toList();
        } catch(IOException e) {
            throw new UncheckedIOException("Error to read file.", e);
        }
    }
}
