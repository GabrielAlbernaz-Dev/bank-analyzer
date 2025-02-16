package com.github.gabrielalbernazdev.infra.exception;

public class BankTransactionParserException extends RuntimeException {
    public BankTransactionParserException(String errorMessage) {
        super(errorMessage);
    }

    public BankTransactionParserException(Throwable err) {
        super(err);
    }

    public BankTransactionParserException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
