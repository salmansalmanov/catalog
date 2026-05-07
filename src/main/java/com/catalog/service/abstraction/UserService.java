package com.catalog.service.abstraction;

import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.result.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    DataResult<UserResponse> save(RegisterRequest registerRequest, MultipartFile profilePhoto);
}
