package com.github.gabrielalbernazdev.bank.infra.parser;

import com.github.gabrielalbernazdev.bank.domain.model.BankTransaction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankTransactionJSONParser implements BankTransactionParser {
    @Override
    public List<BankTransaction> parse(List<String> lines) {
        List<BankTransaction> transactions = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }

        String jsonString = sb.toString();

        JSONObject json = new JSONObject(jsonString);

        JSONArray transactionsArr = json.getJSONArray("transactions");

        for (int i = 0; i < transactionsArr.length(); i++) {
            JSONObject transaction = transactionsArr.getJSONObject(i);

            UUID id = UUID.fromString(transaction.getString("id"));
            double amount = transaction.getDouble("amount");
            String description = transaction.getString("description");
            LocalDateTime createdAt = LocalDateTime.parse(transaction.getString("createdAt"));

            transactions.add(new BankTransaction(id, amount, description, createdAt));
        }

        return transactions;
    }
}
