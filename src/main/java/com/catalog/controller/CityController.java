package com.catalog.controller;

import com.catalog.model.dto.response.CityResponse;
import com.catalog.model.result.DataResult;
import com.catalog.service.abstraction.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<DataResult<List<CityResponse>>> getAllCities() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cityService.getAll());
    }
}
