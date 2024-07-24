package dev.whyneet.ec_api.core.dtos.seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSellerDto(
        @NotBlank(message = "Business name must not be blank.") @Size(max = 1000, message = "Business name must be at most 1000 characters long.") String businessName,
        @NotBlank(message = "Short business name must not be blank.") @Size(max = 32, message = "Short business name must be at most 1000 characters long.") String businessShortName,
        @NotBlank(message = "Taxpayer ID must not be blank.") String taxId,
        @Size(max = 72, min = 8, message = "Password must be 8 - 72 characters long.") String password) {
}
