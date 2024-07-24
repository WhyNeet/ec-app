package dev.whyneet.ec_api.core.abstracts;

import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;

import java.util.Map;

public interface IJwtEncoder {
    String encodeToken(Map<String, Object> claims, String id, String subject, TokenAudience audience, TokenType tokenType);
}
