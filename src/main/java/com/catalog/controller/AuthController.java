package com.catalog.controller;

import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.result.DataResult;
import com.catalog.service.abstraction.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
}
