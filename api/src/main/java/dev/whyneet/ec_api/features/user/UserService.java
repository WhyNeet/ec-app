package dev.whyneet.ec_api.features.user;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.dtos.user.CreateUserDto;
import dev.whyneet.ec_api.core.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IDataServices dataServices;
    @Autowired
    private UserFactory userFactory;

    public Optional<User> getUserById(String id) {
        return dataServices.users().getById(id);
    }

    public User createUser(CreateUserDto createUserDto) {
        User user = userFactory.fromCreateDto(createUserDto);


        return dataServices.users().save(user);
    }
}
