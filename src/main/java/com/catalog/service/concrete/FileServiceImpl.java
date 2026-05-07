package com.catalog.service.concrete;

import com.catalog.file.FileStorage;
import com.catalog.model.dto.response.FileSaveDetail;
import com.catalog.model.entity.Image;
import com.catalog.repository.ImageRepository;
import com.catalog.service.abstraction.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileStorage fileStorage;
    private final ImageRepository imageRepository;

    @Override
    public Image upload(MultipartFile file) {
        FileSaveDetail fileSaveDetail = fileStorage.store(file);
        return saveFile(fileSaveDetail);
    }

    private Image saveFile(FileSaveDetail fileSaveDetail) {
        return imageRepository.save(Image.builder()
                .changedFileName(fileSaveDetail.getChangedFileName())
                .originalFileName(fileSaveDetail.getOriginalFileName())
                .build());
    }
}
