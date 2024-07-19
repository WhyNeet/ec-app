package dev.whyneet.ec_api.frameworks.data.mongo.repository;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.entities.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String>, IDataRepository<Token, String> {
}
