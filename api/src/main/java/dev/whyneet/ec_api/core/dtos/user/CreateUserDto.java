package dev.whyneet.ec_api.core.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank(message = "Name cannot be blank.") @Size(max = 100, message = "Name can be at most 100 characters long.") String name,
        @NotBlank(message = "Email cannot be blank.") @Email(message = "Must be an email.") String email,
        @Size(max = 72, min = 8, message = "Password must be 8 - 72 characters long.") String password,
        @NotBlank(message = "Address cannot be blank.") @Size(max = 1000, message = "Address can be at most 1000 characters long.") String address) {
}
