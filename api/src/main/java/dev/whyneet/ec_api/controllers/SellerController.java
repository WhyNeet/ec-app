package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.seller.SellerDto;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.seller.SellerFactory;
import dev.whyneet.ec_api.frameworks.auth.validation.RequireAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerFactory sellerFactory;

    @RequireAuthentication(audience = TokenAudience.Seller)
    @GetMapping("/current")
    public ResponseEntity<SellerDto> getCurrentSeller(@AuthenticationPrincipal Seller seller) {
        return ResponseEntity.ok(sellerFactory.toDto(seller));
    }
}
