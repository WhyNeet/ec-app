package dev.whyneet.ec_api.frameworks.data.mongo;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.frameworks.data.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class MongoDataServices implements IDataServices {
    @Autowired
    private UserRepository usersRepository;

    @Override
    public IDataRepository<User, String> users() {
        return this.usersRepository;
    }
}
