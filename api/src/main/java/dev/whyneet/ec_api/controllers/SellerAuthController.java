package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.seller.CreateSellerDto;
import dev.whyneet.ec_api.core.dtos.seller.SellerCredentialsDto;
import dev.whyneet.ec_api.core.dtos.seller.SellerDto;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.seller.SellerFactory;
import dev.whyneet.ec_api.features.seller.SellerService;
import dev.whyneet.ec_api.features.token.TokenService;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import dev.whyneet.ec_api.frameworks.exception.exceptions.AuthException;
import dev.whyneet.ec_api.frameworks.exception.exceptions.SellerException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/seller")
public class SellerAuthController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerFactory sellerFactory;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<SellerDto> createSeller(@RequestBody @Validated CreateSellerDto createSellerDto, HttpServletResponse response) throws SellerException.SellerAlreadyExists {
        Seller seller = sellerService.createSeller(createSellerDto);

        TokenPair tokenPair = tokenService.generateTokenPair(TokenAudience.Seller, seller.getId(), null);
        tokenService.setCookies(response, tokenPair);

        return ResponseEntity.ok(sellerFactory.toDto(seller));
    }

    @PostMapping("/login")
    public ResponseEntity<SellerDto> sellerLogin(@RequestBody @Validated SellerCredentialsDto sellerCredentialsDto, HttpServletResponse response) throws SellerException.SellerDoesNotExist, AuthException.WrongSellerCredentials {
        Optional<Seller> seller = sellerService.getSellerByShortBusinessName(sellerCredentialsDto.businessShortName());

        if (seller.isEmpty()) throw new SellerException.SellerDoesNotExist();
        if (!passwordEncoder.matches(sellerCredentialsDto.password(), seller.get().getPassword()))
            throw new AuthException.WrongSellerCredentials();

        TokenPair tokenPair = tokenService.generateTokenPair(TokenAudience.Seller, seller.get().getId(), null);
        tokenService.setCookies(response, tokenPair);

        return ResponseEntity.ok(sellerFactory.toDto(seller.get()));
    }
}
