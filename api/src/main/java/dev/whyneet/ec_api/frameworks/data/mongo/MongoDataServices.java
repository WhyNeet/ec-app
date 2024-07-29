package dev.whyneet.ec_api.frameworks.data.mongo;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.entities.*;
import dev.whyneet.ec_api.frameworks.data.mongo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;


public class MongoDataServices implements IDataServices {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

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

    @Override
    public IDataRepository<Product, String> products() {
        return productRepository;
    }

    @Override
    public IDataRepository<Order, String> orders() {
        return orderRepository;
    }
}
