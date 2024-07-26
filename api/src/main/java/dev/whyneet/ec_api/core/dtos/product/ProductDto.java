package dev.whyneet.ec_api.core.dtos.product;

import dev.whyneet.ec_api.core.dtos.seller.SellerDto;

import java.util.List;
import java.util.Optional;

public record ProductDto(String id, String name, String description, Integer price, List<String> images,
                         Optional<SellerDto> seller) {
}
