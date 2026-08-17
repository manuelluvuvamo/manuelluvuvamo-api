package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.domain.Skill;
import com.manuelluvuvamo.portfolio.repository.SkillRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SkillService extends CrudService<Skill> {

    public SkillService(SkillRepository repository) {
        super(repository, "Competencia");
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.asc("category"), Sort.Order.asc("name"));
    }
}
