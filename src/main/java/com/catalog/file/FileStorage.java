package com.catalog.file;

import com.catalog.model.dto.response.FileSaveDetail;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
    FileSaveDetail store(MultipartFile file);
}
