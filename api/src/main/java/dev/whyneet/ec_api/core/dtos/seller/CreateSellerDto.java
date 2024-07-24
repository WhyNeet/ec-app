package dev.whyneet.ec_api.core.dtos.seller;

public record CreateSellerDto(String businessName, String businessShortName, String taxId, String password) {
}
