package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.common.BaseDocument;
import com.manuelluvuvamo.portfolio.common.ContentStatus;
import com.manuelluvuvamo.portfolio.repository.StatusRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

/** CRUD com filtro de publicacao para a API publica. */
public abstract class ContentService<T extends BaseDocument> extends CrudService<T> {

    private final StatusRepository<T> statusRepository;

    protected ContentService(StatusRepository<T> repository, String resourceName) {
        super(repository, resourceName);
        this.statusRepository = repository;
    }

    /** Ordenacao vista pelos visitantes do site. Por omissao e a do dashboard. */
    protected Sort publicSort() {
        return defaultSort();
    }

    public List<T> findPublished() {
        return statusRepository.findByStatus(ContentStatus.PUBLISHED, publicSort());
    }
}
