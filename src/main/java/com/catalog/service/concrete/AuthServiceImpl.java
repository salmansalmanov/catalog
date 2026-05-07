package com.catalog.service.concrete;

import com.catalog.exception.custom.AlreadyExistsException;
import com.catalog.exception.custom.NotFoundException;
import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.result.DataResult;
import com.catalog.repository.CityRepository;
import com.catalog.repository.UserRepository;
import com.catalog.service.abstraction.AuthService;
import com.catalog.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final UserService userService;

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
}
