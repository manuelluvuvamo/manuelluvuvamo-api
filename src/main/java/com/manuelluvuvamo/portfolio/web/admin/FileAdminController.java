package com.manuelluvuvamo.portfolio.web.admin;

import com.manuelluvuvamo.portfolio.service.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/files")
@Tag(name = "Admin · Ficheiros")
@SecurityRequirement(name = "bearer-jwt")
public class FileAdminController {

    private final FileStorageService storage;

    public FileAdminController(FileStorageService storage) {
        this.storage = storage;
    }

    @GetMapping
    public List<FileStorageService.StoredFile> list() {
        return storage.findAll();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<FileStorageService.StoredFile> upload(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storage.store(file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        storage.delete(id);
        return ResponseEntity.noContent().build();
    }
}
