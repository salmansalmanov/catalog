package com.catalog.service.concrete;

import com.catalog.exception.custom.NotFoundException;
import com.catalog.mapper.ImageMapper;
import com.catalog.mapper.UserMapper;
import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.entity.City;
import com.catalog.model.entity.Image;
import com.catalog.model.entity.User;
import com.catalog.model.result.DataResult;
import com.catalog.model.result.SuccessDataResult;
import com.catalog.repository.CityRepository;
import com.catalog.repository.UserRepository;
import com.catalog.service.abstraction.FileService;
import com.catalog.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final CityRepository cityRepository;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DataResult<UserResponse> save(RegisterRequest registerRequest, MultipartFile profilePhoto) {
        User user = userMapper.toEntity(registerRequest);
        City city = cityRepository.findById(registerRequest.getCityId())
                .orElseThrow(() -> new NotFoundException("City not found"));
        user.setCity(city);
        Image image = fileService.upload(profilePhoto);
        user.setProfilePhoto(image);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);
        return new SuccessDataResult<>(userMapper.toResponse(savedUser), "User created successfully");
    }
}
