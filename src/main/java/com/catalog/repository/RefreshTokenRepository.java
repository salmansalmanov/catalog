package com.catalog.repository;

import com.catalog.model.entity.RefreshTokenEntity;
import com.catalog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByUser(User user);
}
