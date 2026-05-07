package com.catalog.repository;

import com.catalog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);
}
