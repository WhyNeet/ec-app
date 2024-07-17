package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.Token;
import io.jsonwebtoken.JwtException;

import java.security.Key;

public interface IJwtDecoder {
    Token decodeToken(String token, Key key) throws JwtException;
}
