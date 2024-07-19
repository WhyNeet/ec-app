package dev.whyneet.ec_api.frameworks.data.mongo.repository;


import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>, IDataRepository<User, String> {
}
