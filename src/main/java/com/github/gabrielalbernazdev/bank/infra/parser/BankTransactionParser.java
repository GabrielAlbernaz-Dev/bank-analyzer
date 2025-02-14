package com.github.gabrielalbernazdev.bank.infra.parser;

import com.github.gabrielalbernazdev.bank.domain.model.BankTransaction;

import java.util.List;

public interface BankTransactionParser {
    List<BankTransaction> parse(List<String> lines);
}
