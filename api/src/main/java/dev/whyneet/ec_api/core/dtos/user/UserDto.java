package dev.whyneet.ec_api.core.dtos.user;

import dev.whyneet.ec_api.core.entities.User;

public record UserDto(String id, String name, String email, String address) {
}
