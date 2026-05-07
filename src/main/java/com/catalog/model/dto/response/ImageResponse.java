package com.catalog.model.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private UUID id;
    private String originalFileName;
    private String changedFileName;
    private String path;
}
