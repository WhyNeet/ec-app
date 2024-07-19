package dev.whyneet.ec_api.features.user;

import dev.whyneet.ec_api.core.dtos.user.CreateUserDto;
import dev.whyneet.ec_api.core.dtos.user.UserDto;
import dev.whyneet.ec_api.core.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAddress());
    }

    public User fromCreateDto(CreateUserDto createUserDto) {
        return new User(null, createUserDto.name(), createUserDto.password(), createUserDto.email(), createUserDto.address());
    }
}
