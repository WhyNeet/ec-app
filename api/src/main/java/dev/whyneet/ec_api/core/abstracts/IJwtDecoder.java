package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import io.jsonwebtoken.JwtException;

public interface IJwtDecoder {
    Token decodeToken(String token, TokenType tokenType) throws JwtException;
}
