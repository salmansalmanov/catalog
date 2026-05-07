package com.catalog.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileSaveDetail {
    private String originalFileName;
    private String changedFileName;
    private String path;
}
