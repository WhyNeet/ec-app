package dev.whyneet.ec_api.core.dtos.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateProductDto(
        @NotBlank(message = "Name cannot be blank.") @Size(min = 1, max = 1000, message = "Name must be between 1 and 1000 characters long") String name,
        @NotBlank(message = "Description cannot be blank.") @Size(min = 1, max = 5000, message = "Description must be between 1 and 5000 characters long.") String description,
        @Min(value = 0, message = "Price must be at least 0 USD.") Integer price, List<String> images) {
}
