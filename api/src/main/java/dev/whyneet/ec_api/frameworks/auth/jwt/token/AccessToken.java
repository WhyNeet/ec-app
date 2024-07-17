package dev.whyneet.ec_api.frameworks.auth.jwt.token;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtConstants;

import java.util.HashMap;

public class AccessToken extends Token {
    public AccessToken(String id, String subject) {
        super(id, subject);
    }

    public static AccessToken create(RefreshToken refreshToken) {
        return new AccessToken(refreshToken.getId(), refreshToken.getSubject());
    }

    public static AccessToken decode(String token, IJwtDecoder decoder) {
        Token parsedToken = decoder.decodeToken(token, JwtConstants.JWT_AT_KEY);
        return (AccessToken) parsedToken;
    }

    public String encode(RefreshToken refreshToken, IJwtEncoder encoder) {
        return encoder.encodeToken(new HashMap<>(), getId(), getSubject(), JwtConstants.JWT_AT_MAX_AGE, JwtConstants.JWT_AT_KEY);
    }
}
