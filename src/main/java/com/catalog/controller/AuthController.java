package com.catalog.controller;

import com.catalog.model.dto.request.LoginRequest;
import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.request.TokenRefreshRequest;
import com.catalog.model.dto.response.LoginResponse;
import com.catalog.model.dto.response.TokenRefreshResponse;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.result.DataResult;
import com.catalog.service.abstraction.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<DataResult<UserResponse>> register(
            @RequestPart("data") @Valid RegisterRequest registerRequest,
            @RequestPart("image") MultipartFile profilePhoto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(registerRequest, profilePhoto));
    }

    @PostMapping("/login")
    public ResponseEntity<DataResult<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<DataResult<TokenRefreshResponse>> refresh(@RequestBody @Valid TokenRefreshRequest tokenRefreshRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.refresh(tokenRefreshRequest));
    }
}
