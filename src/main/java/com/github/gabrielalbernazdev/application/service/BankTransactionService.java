package com.github.gabrielalbernazdev.application.service;

import com.github.gabrielalbernazdev.domain.model.BankTransaction;
import com.github.gabrielalbernazdev.infra.exception.BankTransactionParserException;

import jakarta.servlet.http.Part;

import java.util.List;

public interface BankTransactionService {
    public List<BankTransaction> processBankTransactionsFile(Part requestPartFile) throws BankTransactionParserException;
}
