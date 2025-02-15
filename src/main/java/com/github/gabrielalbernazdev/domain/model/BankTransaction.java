package com.github.gabrielalbernazdev.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankTransaction {
    private final UUID id;
    private double amount;
    private String description;
    private LocalDateTime createdAt;

    public BankTransaction(UUID id, double amount, String description, LocalDateTime createdAt) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    public BankTransaction(double amount, String description) {
        this.id = UUID.randomUUID();
        this.amount = amount;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BankTransaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public BankTransaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
