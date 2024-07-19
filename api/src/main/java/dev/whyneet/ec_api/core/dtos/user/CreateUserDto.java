package dev.whyneet.ec_api.core.dtos.user;

public record CreateUserDto(String name, String email, String password, String address) {
}
