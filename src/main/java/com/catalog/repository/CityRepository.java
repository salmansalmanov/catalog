package com.catalog.repository;

import com.catalog.model.entity.City;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    boolean existsById(@NonNull UUID id);

    Optional<City> findByName(String name);

    boolean existsByName(String name);
}
