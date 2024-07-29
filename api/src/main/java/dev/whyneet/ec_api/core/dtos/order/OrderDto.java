package dev.whyneet.ec_api.core.dtos.order;

public record OrderDto(String id, String productId, String userId, Integer quantity) {
}
