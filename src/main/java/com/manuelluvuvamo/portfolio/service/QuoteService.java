package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.domain.Quote;
import com.manuelluvuvamo.portfolio.repository.QuoteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuoteService extends ContentService<Quote> {

    public QuoteService(QuoteRepository repository) {
        super(repository, "Frase");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("createdAt"));
    }
}
