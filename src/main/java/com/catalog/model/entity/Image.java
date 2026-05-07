package com.catalog.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image extends BaseEntity {
    private String originalFileName;
    private String changedFileName;
}
