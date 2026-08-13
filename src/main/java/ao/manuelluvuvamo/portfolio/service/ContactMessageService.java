package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.domain.ContactMessage;
import ao.manuelluvuvamo.portfolio.repository.ContactMessageRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService extends CrudService<ContactMessage> {

    private final ContactMessageRepository messages;

    public ContactMessageService(ContactMessageRepository repository) {
        super(repository, "Mensagem");
        this.messages = repository;
    }

    @Override
    protected Sort defaultSort() {
        return Sort.by(Sort.Order.desc("createdAt"));
    }

    /** Submissao publica: os estados de leitura e arquivo nunca vem do visitante. */
    public ContactMessage receive(ContactMessage message) {
        message.setRead(false);
        message.setArchived(false);
        return create(message);
    }

    public List<ContactMessage> findInbox() {
        return messages.findByArchived(false, defaultSort());
    }

    public ContactMessage markRead(String id, boolean read) {
        ContactMessage message = findById(id);
        message.setRead(read);
        return messages.save(message);
    }

    public ContactMessage archive(String id, boolean archived) {
        ContactMessage message = findById(id);
        message.setArchived(archived);
        return messages.save(message);
    }

    public long unreadCount() {
        return messages.countByReadFalseAndArchivedFalse();
    }
}
