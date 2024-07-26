package dev.whyneet.ec_api.features.product;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.dtos.product.CreateProductDto;
import dev.whyneet.ec_api.core.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private IDataServices dataServices;

    public Product createProduct(CreateProductDto createProductDto, String sellerId) {
        Product product = productFactory.fromCreateDto(createProductDto, sellerId);

        return dataServices.products().save(product);
    }
}
