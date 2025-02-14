package com.github.gabrielalbernazdev.bank.application.filter;

import com.github.gabrielalbernazdev.bank.domain.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
