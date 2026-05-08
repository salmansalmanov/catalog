package com.catalog.service.concrete;

import com.catalog.exception.custom.AlreadyExistsException;
import com.catalog.exception.custom.NotFoundException;
import com.catalog.mapper.RefreshTokenMapper;
import com.catalog.model.dto.request.LoginRequest;
import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.LoginResponse;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.entity.RefreshTokenEntity;
import com.catalog.model.entity.User;
import com.catalog.model.result.DataResult;
import com.catalog.model.result.SuccessDataResult;
import com.catalog.repository.CityRepository;
import com.catalog.repository.RefreshTokenRepository;
import com.catalog.repository.UserRepository;
import com.catalog.service.abstraction.AuthService;
import com.catalog.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Override
    public DataResult<UserResponse> register(RegisterRequest registerRequest, MultipartFile profilePhoto) {
        if (profilePhoto == null) {
            throw new RuntimeException("Profile photo is null");
        }

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new AlreadyExistsException("Username is already in use");
        }

        if (!cityRepository.existsById(registerRequest.getCityId())) {
            throw new NotFoundException("City not found");
        }

        if (userRepository.existsByPhoneNumber(registerRequest.getPhoneNumber())) {
            throw new AlreadyExistsException("Phone number is already in use");
        }

        return userService.save(registerRequest, profilePhoto);
    }

    @Override
    @Transactional
    public DataResult<LoginResponse> login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found with username: " + loginRequest.getUsername()));
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRole());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getRole());
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByUser(user)
                .map(foundRefreshTokenEntity -> {
                    foundRefreshTokenEntity.setToken(refreshToken);
                    foundRefreshTokenEntity.setExpiration(LocalDateTime.now().plus(refreshTokenExpiration, ChronoUnit.MILLIS));
                    foundRefreshTokenEntity.setRevoked(false);
                    return foundRefreshTokenEntity;
                })
                .orElseGet(() -> refreshTokenMapper.toEntity(user, refreshToken));
        refreshTokenRepository.save(refreshTokenEntity);
        LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken);
        return new SuccessDataResult<>(loginResponse, "Login successful");
    }
}
