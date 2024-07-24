package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.user.CreateUserDto;
import dev.whyneet.ec_api.core.dtos.user.UserCredentialsDto;
import dev.whyneet.ec_api.core.dtos.user.UserDto;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.token.TokenService;
import dev.whyneet.ec_api.features.user.UserFactory;
import dev.whyneet.ec_api.features.user.UserService;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import dev.whyneet.ec_api.frameworks.exception.exceptions.AuthException;
import dev.whyneet.ec_api.frameworks.exception.exceptions.UserException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody @Validated CreateUserDto createUserDto, HttpServletResponse response) throws UserException.UserAlreadyExists {
        User user = userService.createUser(createUserDto);

        TokenPair tokenPair = tokenService.generateTokenPair(TokenAudience.User, user.getId(), null);
        tokenService.setCookies(response, tokenPair);

        return ResponseEntity.ok(userFactory.toDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Validated UserCredentialsDto userCredentialsDto, HttpServletResponse response) throws UserException.UserDoesNotExist, AuthException.WrongUserCredentials {
        Optional<User> user = userService.findUserByEmail(userCredentialsDto.email());

        if (user.isEmpty()) throw new UserException.UserDoesNotExist();
        if (!passwordEncoder.matches(userCredentialsDto.password(), user.get().getPassword())) throw new AuthException.WrongUserCredentials();

        TokenPair tokenPair = tokenService.generateTokenPair(TokenAudience.User, user.get().getId(), null);
        tokenService.setCookies(response, tokenPair);

        return ResponseEntity.ok(userFactory.toDto(user.get()));
    }
}
