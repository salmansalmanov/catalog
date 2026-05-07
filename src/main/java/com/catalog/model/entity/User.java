package com.catalog.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String surname;
    private String username;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Image profilePhoto;
}
