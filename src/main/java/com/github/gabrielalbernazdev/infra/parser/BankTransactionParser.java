package com.github.gabrielalbernazdev.infra.parser;

import com.github.gabrielalbernazdev.domain.model.BankTransaction;

import java.util.List;

public interface BankTransactionParser {
    List<BankTransaction> parse(List<String> lines);
}
