package com.catalog.initializer;

import com.catalog.model.entity.City;
import com.catalog.model.entity.Street;
import com.catalog.repository.CityRepository;
import com.catalog.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityStreetInitializer implements CommandLineRunner {
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;

    @Override
    public void run(String @NonNull ... args) {
        City baku = getOrCreateCity("Baku");
        City sumqayit = getOrCreateCity("Sumqayit");
        City khirdalan = getOrCreateCity("Khirdalan");

        if (!streetRepository.existsByCity(baku)) {
            List<Street> streets = List.of(
                    new Street("28 May küçəsi", baku),
                    new Street("Rəşid Behbudov küçəsi", baku),
                    new Street("Şəmsi Bədəlbəyli küçəsi", baku),
                    new Street("Zahid Xəlilov küçəsi", baku),
                    new Street("Yusif Səfərov küçəsi", baku)
            );
            streetRepository.saveAll(streets);
        }

        if (!streetRepository.existsByCity(sumqayit)) {
            List<Street> streets = List.of(
                    new Street("Sülh küçəsi", sumqayit),
                    new Street("Koroğlu küçəsi", sumqayit),
                    new Street("Cəfər Cabbarlı küçəsi", sumqayit),
                    new Street("Nəriman Nərimanov küçəsi", sumqayit),
                    new Street("Babək küçəsi", sumqayit)
            );
            streetRepository.saveAll(streets);
        }

        if (!streetRepository.existsByCity(khirdalan)) {
            List<Street> streets = List.of(
                    new Street("Hacı Zeynalabdin Tağıyev küçəsi", khirdalan),
                    new Street("Xəlil Rza Ulutürk küçəsi", khirdalan),
                    new Street("Vüsal Əliyev küçəsi", khirdalan),
                    new Street("Tofiq İsmayılov küçəsi", khirdalan),
                    new Street("Qəzənfər Xalıqov küçəsi", khirdalan)
            );
            streetRepository.saveAll(streets);
        }
    }

    private City getOrCreateCity(String name) {
        return cityRepository.findByName(name)
                .orElseGet(() -> cityRepository.save(City.builder().name(name).build()));
    }
}