package dev.whyneet.ec_api.frameworks.auth.jwt.token;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import io.jsonwebtoken.JwtException;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
public class AccessToken extends Token {
    public static final String COOKIE_NAME = "access_token";

    public AccessToken(String id, String subject) {
        super(id, subject);
    }

    public AccessToken(String id, String subject, TokenAudience audience) {
        super(id, subject, audience);
    }

    public static AccessToken create(RefreshToken refreshToken) {
        return new AccessToken(refreshToken.getId(), refreshToken.getSubject(), refreshToken.getAudience());
    }

    public static AccessToken decode(String token, IJwtDecoder decoder) throws JwtException {
        Token parsedToken = decoder.decodeToken(token, TokenType.ACCESS);
        return new AccessToken(parsedToken.getId(), parsedToken.getSubject());
    }

    public String encode(IJwtEncoder encoder) {
        return encoder.encodeToken(new HashMap<>(), getId(), getSubject(), getAudience(), TokenType.ACCESS);
    }
}
