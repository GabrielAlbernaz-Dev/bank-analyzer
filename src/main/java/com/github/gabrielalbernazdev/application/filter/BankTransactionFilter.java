package com.github.gabrielalbernazdev.application.filter;

import com.github.gabrielalbernazdev.domain.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
