package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.common.ContentStatus;
import com.manuelluvuvamo.portfolio.domain.Essay;
import com.manuelluvuvamo.portfolio.repository.EssayRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EssayService extends SluggedContentService<Essay> {

    public EssayService(EssayRepository repository) {
        super(repository, "Ensaio");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.desc("publishedAt"), Sort.Order.desc("createdAt"));
    }

    @Override
    protected void prepare(Essay essay) {
        super.prepare(essay);
        if (essay.getStatus() == ContentStatus.PUBLISHED && essay.getPublishedAt() == null) {
            essay.setPublishedAt(Instant.now());
        }
    }
}
