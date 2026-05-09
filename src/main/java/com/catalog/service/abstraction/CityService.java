package com.catalog.service.abstraction;

import com.catalog.model.dto.response.CityResponse;
import com.catalog.model.result.DataResult;

import java.util.List;

public interface CityService {
    DataResult<List<CityResponse>> getAll();
}
