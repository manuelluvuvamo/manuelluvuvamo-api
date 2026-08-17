package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.domain.Experience;
import com.manuelluvuvamo.portfolio.repository.ExperienceRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService extends CrudService<Experience> {

    public ExperienceService(ExperienceRepository repository) {
        super(repository, "Experiencia");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("startDate"));
    }

    @Override
    protected void prepare(Experience experience) {
        // Um cargo em curso nao tem data de fim, e vice-versa.
        if (experience.isCurrent()) {
            experience.setEndDate(null);
        } else if (experience.getEndDate() == null) {
            experience.setCurrent(true);
        }
    }
}
