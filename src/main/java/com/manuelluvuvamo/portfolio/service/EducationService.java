package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.domain.Education;
import com.manuelluvuvamo.portfolio.repository.EducationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EducationService extends CrudService<Education> {

    public EducationService(EducationRepository repository) {
        super(repository, "Formacao");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("startDate"));
    }

    @Override
    protected void prepare(Education education) {
        if (education.isCurrent()) {
            education.setEndDate(null);
        } else if (education.getEndDate() == null) {
            education.setCurrent(true);
        }
    }
}
