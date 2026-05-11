package com.catalog.repository;

import com.catalog.model.entity.City;
import com.catalog.model.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StreetRepository extends JpaRepository<Street, UUID> {
    boolean existsByCity(City city);
}
