package com.catalog.mapper;

import com.catalog.model.dto.response.CityResponse;
import com.catalog.model.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityResponse toResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
