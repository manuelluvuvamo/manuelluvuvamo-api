package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.common.ContentStatus;
import com.manuelluvuvamo.portfolio.domain.Vlog;
import com.manuelluvuvamo.portfolio.repository.VlogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class VlogService extends SluggedContentService<Vlog> {

    public VlogService(VlogRepository repository) {
        super(repository, "Vlog");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.desc("publishedAt"), Sort.Order.desc("createdAt"));
    }

    @Override
    protected void prepare(Vlog vlog) {
        super.prepare(vlog);
        if (vlog.getStatus() == ContentStatus.PUBLISHED && vlog.getPublishedAt() == null) {
            vlog.setPublishedAt(Instant.now());
        }
    }
}
