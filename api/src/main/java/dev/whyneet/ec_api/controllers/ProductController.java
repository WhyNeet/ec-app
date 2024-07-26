package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.product.CreateProductDto;
import dev.whyneet.ec_api.core.dtos.product.ProductDto;
import dev.whyneet.ec_api.core.entities.Product;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.product.ProductFactory;
import dev.whyneet.ec_api.features.product.ProductService;
import dev.whyneet.ec_api.frameworks.auth.validation.RequireAuthentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private ProductService productService;

    @RequireAuthentication(audience = TokenAudience.Seller)
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Validated CreateProductDto createProductDto, @AuthenticationPrincipal Seller seller) {
        Product product = productService.createProduct(createProductDto, new ObjectId(seller.getId()));

        return ResponseEntity.ok(productFactory.toDto(product, seller));
    }
}
