package com.manuelluvuvamo.portfolio.service;

import com.manuelluvuvamo.portfolio.common.BaseDocument;
import com.manuelluvuvamo.portfolio.error.ApiException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * CRUD comum a todas as coleccoes de conteudo. As subclasses so definem a
 * ordenacao por omissao e, se precisarem, normalizacoes em {@link #prepare}.
 */
public abstract class CrudService<T extends BaseDocument> {

    protected final MongoRepository<T, String> repository;
    private final String resourceName;

    protected CrudService(MongoRepository<T, String> repository, String resourceName) {
        this.repository = repository;
        this.resourceName = resourceName;
    }

    /** Ordenacao usada nas listagens do dashboard. */
    protected abstract Sort defaultSort();

    /** Gancho para normalizar o documento antes de gravar (slugs, datas, etc.). */
    protected void prepare(T entity) {
        // Sem normalizacao por omissao.
    }

    /**
     * Gancho para salvar campos que o dashboard nao envia e que se perderiam
     * na substituicao total — contadores, por exemplo.
     */
    protected void carryOver(T existing, T incoming) {
        // Nada a preservar por omissao.
    }

    public List<T> findAll() {
        return repository.findAll(defaultSort());
    }

    public T findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> ApiException.notFound(resourceName, id));
    }

    public T create(T entity) {
        entity.setId(null);
        prepare(entity);
        return repository.save(entity);
    }

    /**
     * Substituicao total do documento. O identificador e a data de criacao
     * originais sao preservados, tudo o resto vem do cliente.
     */
    public T update(String id, T entity) {
        T existing = findById(id);
        entity.setId(existing.getId());
        entity.setCreatedAt(existing.getCreatedAt());
        carryOver(existing, entity);
        prepare(entity);
        return repository.save(entity);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw ApiException.notFound(resourceName, id);
        }
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    protected String resourceName() {
        return resourceName;
    }
}
