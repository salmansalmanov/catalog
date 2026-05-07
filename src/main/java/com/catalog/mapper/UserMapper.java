package com.catalog.mapper;

import com.catalog.model.dto.request.RegisterRequest;
import com.catalog.model.dto.response.UserResponse;
import com.catalog.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final CityMapper cityMapper;

    @Value("${spring.catalog.path}")
    private String path;

    public User toEntity(RegisterRequest registerRequest) {
        return User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .username(registerRequest.getUsername())
                .phoneNumber(registerRequest.getPhoneNumber())
                .build();
    }

    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .city(cityMapper.toResponse(user.getCity()))
                .profilePhotoUrl(path + user.getProfilePhoto().getChangedFileName())
                .build();
    }
}
