package com.catalog.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "streets")
public class Street extends BaseEntity {
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
}
