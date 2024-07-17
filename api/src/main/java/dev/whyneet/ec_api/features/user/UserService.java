package dev.whyneet.ec_api.features.user;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IDataServices dataServices;

    public Optional<User> getUserById(String id) {
        return dataServices.users().getById(id);
    }
}
