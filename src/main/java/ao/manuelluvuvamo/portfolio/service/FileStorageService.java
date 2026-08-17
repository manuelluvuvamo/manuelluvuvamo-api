package ao.manuelluvuvamo.portfolio.service;

import ao.manuelluvuvamo.portfolio.error.ApiException;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Guarda as imagens do portfolio no GridFS do proprio MongoDB.
 *
 * <p>Assim nao ha um servico de armazenamento a mais para manter: os
 * ficheiros viajam com a base de dados, nos backups e no docker-compose.</p>
 */
@Service
public class FileStorageService {

    /** Formatos aceites. Deliberadamente curto: isto e para imagens do site. */
    private static final Set<String> ALLOWED_TYPES =
            Set.of("image/jpeg", "image/png", "image/webp", "image/gif", "image/avif", "image/svg+xml");

    private static final long MAX_BYTES = 5 * 1024 * 1024;

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations gridFsOperations;

    public FileStorageService(GridFsTemplate gridFsTemplate, GridFsOperations gridFsOperations) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFsOperations = gridFsOperations;
    }

    public record StoredFile(String id, String filename, String contentType, long size) {
    }

    public StoredFile store(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw ApiException.badRequest("Nenhum ficheiro recebido.");
        }
        if (file.getSize() > MAX_BYTES) {
            throw ApiException.badRequest("O ficheiro passa dos 5 MB.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw ApiException.badRequest(
                    "Formato nao aceite. Usa JPEG, PNG, WebP, GIF, AVIF ou SVG.");
        }

        String filename = StringUtils.getFilename(file.getOriginalFilename());
        try {
            ObjectId id = gridFsTemplate.store(
                    file.getInputStream(),
                    filename == null || filename.isBlank() ? "imagem" : filename,
                    contentType);
            return new StoredFile(id.toHexString(), filename, contentType, file.getSize());
        } catch (IOException ex) {
            throw new ApiException(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR,
                    "Nao foi possivel guardar o ficheiro.");
        }
    }

    public GridFsResource load(String id) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(toObjectId(id))));
        if (file == null) {
            throw ApiException.notFound("Ficheiro", id);
        }
        return gridFsOperations.getResource(file);
    }

    public void delete(String id) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(toObjectId(id))));
    }

    public List<StoredFile> findAll() {
        return gridFsTemplate.find(new Query()).into(new java.util.ArrayList<>()).stream()
                .map(file -> new StoredFile(
                        file.getObjectId().toHexString(),
                        file.getFilename(),
                        file.getMetadata() == null ? null : file.getMetadata().getString("_contentType"),
                        file.getLength()))
                .toList();
    }

    private ObjectId toObjectId(String id) {
        if (!ObjectId.isValid(id)) {
            throw ApiException.notFound("Ficheiro", id);
        }
        return new ObjectId(id);
    }
}
