package com.catalog.service.abstraction;

import com.catalog.model.dto.request.LoginRequest;
import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.LoginResponse;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.result.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    DataResult<UserResponse> register(RegisterRequest registerRequest, MultipartFile profilePhoto);

    DataResult<LoginResponse> login(LoginRequest loginRequest);
}
