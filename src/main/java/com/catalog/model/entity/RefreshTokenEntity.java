package com.catalog.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_tokens")
public class RefreshTokenEntity extends BaseEntity {
    private String token;
    private LocalDateTime expiration;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Boolean revoked;
}
