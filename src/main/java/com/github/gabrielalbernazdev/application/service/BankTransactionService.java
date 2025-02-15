package com.github.gabrielalbernazdev.application.service;

import com.github.gabrielalbernazdev.domain.model.BankTransaction;

import jakarta.servlet.http.Part;

import java.io.IOException;
import java.util.List;

public interface BankTransactionService {
    public List<BankTransaction> processBankTransactionsFile(Part requestPartFile) throws IOException;
}
