package com.catalog.model.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String surname;
    private String username;
    private CityResponse city;
    private String profilePhotoUrl;
}
