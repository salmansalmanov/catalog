package com.catalog.initializer;

import com.catalog.model.entity.City;
import com.catalog.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityInitializer implements CommandLineRunner {
    private final CityRepository cityRepository;

    @Override
    public void run(String @NonNull ... args) {
        createCityIfNotExists("Baku");
        createCityIfNotExists("Sumqayit");
        createCityIfNotExists("Khirdalan");
    }

    private void createCityIfNotExists(String name) {
        if (!cityRepository.existsByName(name)) {
            cityRepository.save(City.builder().name(name).build());
        }
    }
}
