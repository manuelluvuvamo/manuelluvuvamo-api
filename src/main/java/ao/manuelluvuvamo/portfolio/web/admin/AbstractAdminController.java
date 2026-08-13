package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.common.BaseDocument;
import ao.manuelluvuvamo.portfolio.service.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * CRUD do dashboard. Cada subclasse fixa a rota e o servico; o corpo dos
 * pedidos e resolvido a partir do tipo generico da subclasse.
 *
 * <p>Todas estas rotas vivem sob /api/v1/admin e exigem o papel ADMIN,
 * conforme definido em SecurityConfig.</p>
 */
public abstract class AbstractAdminController<T extends BaseDocument> {

    private final CrudService<T> service;

    protected AbstractAdminController(CrudService<T> service) {
        this.service = service;
    }

    @GetMapping
    public List<T> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public T get(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<T> create(@Valid @RequestBody T body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(body));
    }

    @PutMapping("/{id}")
    public T update(@PathVariable String id, @Valid @RequestBody T body) {
        return service.update(id, body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
