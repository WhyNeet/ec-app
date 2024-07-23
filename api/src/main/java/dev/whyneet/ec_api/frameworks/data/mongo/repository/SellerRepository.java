package dev.whyneet.ec_api.frameworks.data.mongo.repository;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.entities.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String>, IDataRepository<Seller, String> {
}
