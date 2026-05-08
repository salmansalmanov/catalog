package com.catalog.mapper;

import com.catalog.model.entity.RefreshTokenEntity;
import com.catalog.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class RefreshTokenMapper {

    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    public RefreshTokenEntity toEntity(User user, String refreshToken) {
        return RefreshTokenEntity.builder()
                .token(refreshToken)
                .user(user)
                .revoked(false)
                .expiration(LocalDateTime.now().plus(refreshTokenExpiration, ChronoUnit.MILLIS))
                .build();
    }
}
