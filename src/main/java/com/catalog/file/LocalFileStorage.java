package com.catalog.file;

import com.catalog.model.dto.response.FileSaveDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class LocalFileStorage implements FileStorage {

    @Override
    public FileSaveDetail store(MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            String uploadDir = "images";

            if (originalFileName != null && originalFileName.contains(".")) {
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            String changedFileName = UUID.randomUUID() + extension;
            Path root = Paths.get(uploadDir);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            Files.copy(file.getInputStream(), root.resolve(changedFileName), StandardCopyOption.REPLACE_EXISTING);

            return FileSaveDetail.builder()
                    .originalFileName(originalFileName)
                    .changedFileName(changedFileName)
                    .path(root.resolve(changedFileName).toString())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file: " + e.getMessage());
        }
    }
}
