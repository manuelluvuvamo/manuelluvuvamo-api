package ao.manuelluvuvamo.portfolio.web;

import ao.manuelluvuvamo.portfolio.service.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;

/** Serve as imagens guardadas no GridFS. */
@RestController
@RequestMapping("/api/v1/public/files")
@Tag(name = "Publico")
public class MediaController {

    private final FileStorageService storage;

    public MediaController(FileStorageService storage) {
        this.storage = storage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> get(@PathVariable String id) throws IOException {
        GridFsResource resource = storage.load(id);

        String contentType = resource.getContentType();
        return ResponseEntity.ok()
                // O identificador nunca muda de conteudo, por isso pode ficar
                // em cache para sempre.
                .cacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic().immutable())
                .contentType(contentType == null
                        ? MediaType.APPLICATION_OCTET_STREAM
                        : MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
