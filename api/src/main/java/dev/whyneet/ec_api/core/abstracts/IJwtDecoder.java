package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.Token;

import java.security.Key;

public interface IJwtDecoder {
    Token decodeToken(String token, Key key);
}
