package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.User;

public interface IDataServices {
    IDataRepository<User, String> users();
}
