package ao.manuelluvuvamo.portfolio.web.admin;

import ao.manuelluvuvamo.portfolio.domain.ContactMessage;
import ao.manuelluvuvamo.portfolio.service.ContactMessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/messages")
@Tag(name = "Admin · Mensagens")
@SecurityRequirement(name = "bearer-jwt")
public class ContactMessageAdminController {

    private final ContactMessageService service;

    public ContactMessageAdminController(ContactMessageService service) {
        this.service = service;
    }

    /** Por omissao devolve so a caixa de entrada; ?archived=true traz o arquivo. */
    @GetMapping
    public List<ContactMessage> list(@RequestParam(defaultValue = "false") boolean archived) {
        return archived ? service.findAll() : service.findInbox();
    }

    @GetMapping("/{id}")
    public ContactMessage get(@PathVariable String id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}/read")
    public ContactMessage markRead(@PathVariable String id, @RequestParam(defaultValue = "true") boolean read) {
        return service.markRead(id, read);
    }

    @PatchMapping("/{id}/archive")
    public ContactMessage archive(@PathVariable String id, @RequestParam(defaultValue = "true") boolean archived) {
        return service.archive(id, archived);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
