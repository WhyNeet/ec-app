package dev.whyneet.ec_api.frameworks.data.mongo.repository;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, IDataRepository<Product, String> {
}
