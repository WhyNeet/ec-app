package dev.whyneet.ec_api.frameworks.data.mongo;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.frameworks.data.mongo.repository.SellerRepository;
import dev.whyneet.ec_api.frameworks.data.mongo.repository.TokenRepository;
import dev.whyneet.ec_api.frameworks.data.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class MongoDataServices implements IDataServices {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public IDataRepository<User, String> users() {
        return usersRepository;
    }

    @Override
    public IDataRepository<Token, String> tokens() {
        return tokenRepository;
    }

    @Override
    public IDataRepository<Seller, String> sellers() {
        return sellerRepository;
    }
}
