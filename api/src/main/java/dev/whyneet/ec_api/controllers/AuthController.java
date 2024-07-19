package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.user.CreateUserDto;
import dev.whyneet.ec_api.core.dtos.user.UserDto;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.user.UserFactory;
import dev.whyneet.ec_api.features.user.UserService;
import dev.whyneet.ec_api.frameworks.exception.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFactory userFactory;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody CreateUserDto createUserDto) throws UserException.UserAlreadyExists {
        User user = userService.createUser(createUserDto);

        return ResponseEntity.ok(userFactory.toDto(user));
    }
}
