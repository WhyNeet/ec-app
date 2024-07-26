package dev.whyneet.ec_api.features.product;

import dev.whyneet.ec_api.core.dtos.product.CreateProductDto;
import dev.whyneet.ec_api.core.dtos.product.ProductDto;
import dev.whyneet.ec_api.core.entities.Product;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.features.seller.SellerFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductFactory {
    @Autowired
    private SellerFactory sellerFactory;

    public ProductDto toDto(Product product, Seller seller) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImages(), seller != null ? Optional.of(sellerFactory.toDto(seller)) : Optional.empty());
    }

    public Product fromCreateDto(CreateProductDto createProductDto, ObjectId objectId) {
        return new Product(null, createProductDto.name(), createProductDto.images(), createProductDto.price(), createProductDto.description(), objectId);
    }
}
