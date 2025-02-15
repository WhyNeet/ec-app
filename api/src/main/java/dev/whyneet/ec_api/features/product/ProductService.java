package dev.whyneet.ec_api.features.product;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.dtos.product.CreateProductDto;
import dev.whyneet.ec_api.core.entities.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private IDataServices dataServices;

    public Product createProduct(CreateProductDto createProductDto, ObjectId sellerId) {
        Product product = productFactory.fromCreateDto(createProductDto, sellerId);

        return dataServices.products().save(product);
    }

    public Optional<Product> getProductById(String productId) {
        return dataServices.products().getById(productId);
    }

    public List<Product> getProductsById(Iterable<String> ids) {
        return dataServices.products().findAllById(ids);
    }

    public List<Product> getProductBySellerId(ObjectId sellerId) {
        return dataServices.products().findAll(Example.of(new Product(null, null, null, null, null, sellerId)));
    }
}
