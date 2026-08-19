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
        // Certificacoes primeiro, e dentro de cada grupo as mais recentes.
        return Sort.by(Sort.Order.asc("kind"), Sort.Order.asc("orderIndex"),
                Sort.Order.desc("issuedAt"));
    }
}
