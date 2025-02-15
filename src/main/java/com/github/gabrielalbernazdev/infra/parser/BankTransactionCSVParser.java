package com.github.gabrielalbernazdev.infra.parser;

import com.github.gabrielalbernazdev.domain.model.BankTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankTransactionCSVParser implements BankTransactionParser {
    @Override
    public List<BankTransaction> parse(List<String> lines) {
        List<BankTransaction> transactions = new ArrayList<>();

        lines.forEach((line) -> {
            String[] columns = line.split(",");
            UUID id = UUID.fromString(columns[0]);
            double amount = Double.parseDouble(columns[1]);
            String description = columns[2];
            LocalDateTime createdAt = LocalDateTime.parse(columns[3]);
            transactions.add(new BankTransaction(id, amount, description, createdAt));
        });

        return transactions;
    }
}
