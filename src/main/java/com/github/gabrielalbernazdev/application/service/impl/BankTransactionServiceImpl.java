package com.github.gabrielalbernazdev.application.service.impl;

import java.io.IOException;
import java.util.List;

import com.github.gabrielalbernazdev.application.service.BankTransactionService;
import com.github.gabrielalbernazdev.domain.model.BankTransaction;
import com.github.gabrielalbernazdev.infra.exception.BankTransactionParserException;
import com.github.gabrielalbernazdev.infra.extractor.FilePartExtractor;
import com.github.gabrielalbernazdev.infra.parser.BankTransactionParser;
import com.github.gabrielalbernazdev.infra.parser.BankTransactionParserFactory;

import jakarta.servlet.http.Part;

public class BankTransactionServiceImpl implements BankTransactionService {
    @Override
    public List<BankTransaction> processBankTransactionsFile(Part requestPartFile) throws BankTransactionParserException {
        final BankTransactionParser parser = BankTransactionParserFactory.getBankTransactionParser(requestPartFile.getContentType());
        List<String> fileLines = null;
        try {
            fileLines = FilePartExtractor.extractFileLines(requestPartFile.getInputStream());
        } catch (IOException e) {
            throw new BankTransactionParserException(e);
        }

        List<BankTransaction> transactions = parser.parse(fileLines);

        return transactions;
    }
}
