package com.catalog.service.abstraction;

import com.catalog.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    Image upload(MultipartFile file);
}
