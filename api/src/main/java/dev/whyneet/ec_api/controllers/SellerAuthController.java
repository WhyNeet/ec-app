package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.seller.CreateSellerDto;
import dev.whyneet.ec_api.core.dtos.seller.SellerDto;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.seller.SellerFactory;
import dev.whyneet.ec_api.features.seller.SellerService;
import dev.whyneet.ec_api.features.token.TokenService;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import dev.whyneet.ec_api.frameworks.exception.exceptions.SellerException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/seller")
public class SellerAuthController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerFactory sellerFactory;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<SellerDto> createSeller(@RequestBody CreateSellerDto createSellerDto, HttpServletResponse response) throws SellerException.SellerAlreadyExists {
        Seller seller = sellerService.createSeller(createSellerDto);

        TokenPair tokenPair = tokenService.generateTokenPair(TokenAudience.Seller, seller.getId(), null);
        tokenService.setCookies(response, tokenPair);

        return ResponseEntity.ok(sellerFactory.toDto(seller));
    }
}
