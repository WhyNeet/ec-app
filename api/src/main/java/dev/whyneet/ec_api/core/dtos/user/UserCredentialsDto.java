package dev.whyneet.ec_api.core.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCredentialsDto(
        @NotBlank(message = "Email cannot be blank.") @Email(message = "Must be an email.") String email,
        @Size(max = 72, min = 8, message = "Password must be 8 - 72 characters long.") String password) {
}
