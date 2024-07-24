package dev.whyneet.ec_api.features.seller;

import dev.whyneet.ec_api.core.dtos.seller.CreateSellerDto;
import dev.whyneet.ec_api.core.dtos.seller.SellerDto;
import dev.whyneet.ec_api.core.entities.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerFactory {
    public Seller fromCreateDto(CreateSellerDto createSellerDto) {
        return new Seller(null, createSellerDto.businessName(), createSellerDto.businessShortName(), createSellerDto.taxId(), createSellerDto.password(), false, null);
    }

    public SellerDto toDto(Seller seller) {
        return new SellerDto(seller.getId(), seller.getBusinessName(), seller.getBusinessShortName(), seller.getDescription(), seller.getVerified());
    }
}
