package com.github.gabrielalbernazdev.bank;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/bank")
@MultipartConfig
public class BankAnalyzerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("XESQUE");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder json = new StringBuilder();
        try(BufferedReader reader = req.getReader()) {
            System.out.println("LUL reader" + req.getReader());

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.println("JSON start" + " " + json);
                json.append(line);
            }

            System.out.println("JSON final" + " " + json);
        }

        resp.setStatus(201);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
    }
}
