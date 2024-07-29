package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.*;

public interface IDataServices {
    IDataRepository<User, String> users();
    IDataRepository<Token, String> tokens();
    IDataRepository<Seller, String> sellers();
    IDataRepository<Product, String> products();
    IDataRepository<Order, String> orders();
}
