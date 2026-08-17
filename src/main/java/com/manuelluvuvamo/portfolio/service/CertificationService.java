package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.domain.Certification;
import com.manuelluvuvamo.portfolio.repository.CertificationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CertificationService extends CrudService<Certification> {

    public CertificationService(CertificationRepository repository) {
        super(repository, "Certificacao");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("issuedAt"));
    }
}
