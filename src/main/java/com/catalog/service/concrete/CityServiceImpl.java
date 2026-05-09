package com.catalog.service.concrete;

import com.catalog.mapper.CityMapper;
import com.catalog.model.dto.response.CityResponse;
import com.catalog.model.result.DataResult;
import com.catalog.model.result.SuccessDataResult;
import com.catalog.repository.CityRepository;
import com.catalog.service.abstraction.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public DataResult<List<CityResponse>> getAll() {
        return new SuccessDataResult<>(cityRepository.findAllByOrderByNameAsc()
                .stream()
                .map(cityMapper::toResponse)
                .toList(), "Cities found successfully");
    }
}
