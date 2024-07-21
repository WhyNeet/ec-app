package dev.whyneet.ec_api.features.user;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.dtos.user.CreateUserDto;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.frameworks.exception.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IDataServices dataServices;
    @Autowired
    private UserFactory userFactory;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Optional<User> getUserById(String id) {
        return dataServices.users().getById(id);
    }

    public User createUser(CreateUserDto createUserDto) throws UserException.UserAlreadyExists {
        User user = userFactory.fromCreateDto(createUserDto);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        try {
            user = dataServices.users().save(user);
        } catch (DuplicateKeyException ex) {
            throw new UserException.UserAlreadyExists();
        }

        return user;
    }
}
