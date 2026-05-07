package com.catalog.model.dto.request;

import com.catalog.validation.annotation.PhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3, max = 20, message = "Surname must be between 3 and 20")
    private String surname;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20")
    private String username;

    @PhoneNumber
    private String phoneNumber;

    @NotNull(message = "City cannot be null")
    private UUID cityId;
}
