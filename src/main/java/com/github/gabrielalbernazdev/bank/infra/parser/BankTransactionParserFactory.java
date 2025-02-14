package com.github.gabrielalbernazdev.bank.infra.parser;

public class BankTransactionParserFactory {
        public static BankTransactionParser getBankTransactionParser(String contentType) {
        if(contentType.contains("application/json")) {
            return new BankTransactionJSONParser();
        }

        if(contentType.contains("application/csv") || contentType.contains("text/csv")) {
            return new BankTransactionCSVParser();
        }

        if(contentType.contains("application/xml") || contentType.contains("text/xml")) {
            return new BankTransactionXMLParser();
        }

        throw new IllegalArgumentException("Invalid content type");
    }
}
