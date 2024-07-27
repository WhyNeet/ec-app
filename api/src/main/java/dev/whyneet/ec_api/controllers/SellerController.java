package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.product.ProductDto;
import dev.whyneet.ec_api.core.dtos.seller.SellerDto;
import dev.whyneet.ec_api.core.entities.Product;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.product.ProductFactory;
import dev.whyneet.ec_api.features.product.ProductService;
import dev.whyneet.ec_api.features.seller.SellerFactory;
import dev.whyneet.ec_api.frameworks.auth.validation.RequireAuthentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerFactory sellerFactory;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductFactory productFactory;

    @RequireAuthentication(audience = TokenAudience.Seller)
    @GetMapping("/current")
    public ResponseEntity<SellerDto> getCurrentSeller(@AuthenticationPrincipal Seller seller) {
        return ResponseEntity.ok(sellerFactory.toDto(seller));
    }

    @GetMapping("/{sellerId}/products")
    public ResponseEntity<List<ProductDto>> getSellerProducts(@PathVariable String sellerId) {
        ObjectId sellerObjectId = new ObjectId(sellerId);

        List<Product> products = productService.getProductBySellerId(sellerObjectId);

        return ResponseEntity.ok(products.stream().map(product -> productFactory.toDto(product, null)).collect(Collectors.toList()));
    }
}
