package com.catalog.mapper;

import com.catalog.model.dto.response.ImageResponse;
import com.catalog.model.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public ImageResponse toResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .originalFileName(image.getOriginalFileName())
                .changedFileName(image.getChangedFileName())
                .path("files/" + image.getChangedFileName())
                .build();
    }
}
