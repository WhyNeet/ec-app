package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.user.UserDto;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.user.UserFactory;
import dev.whyneet.ec_api.frameworks.auth.validation.RequireAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserFactory userFactory;

    @GetMapping("/me")
    @RequireAuthentication
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userFactory.toDto(user));
    }
}
