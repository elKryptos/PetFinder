package hans.startup.petfinderbackend.controllers;

import hans.startup.petfinderbackend.responses.UploadMediaResponse;
import hans.startup.petfinderbackend.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@AllArgsConstructor
public class MediaController {

    private StorageService storageService;

    public UploadMediaResponse path(MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadMediaResponse(path);
    }

    public ResponseEntity<Resource> getResource(String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity.status(200).header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
    }
}