package dev.whyneet.ec_api.core.dtos.product;

import java.util.List;

public record PurchaseProductsDto(List<ProductPurchaseDataDto> products) {
    public record ProductPurchaseDataDto(String id, int quantity) {
    }
}
