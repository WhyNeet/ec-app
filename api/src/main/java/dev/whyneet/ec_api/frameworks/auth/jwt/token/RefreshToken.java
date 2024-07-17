package dev.whyneet.ec_api.frameworks.auth.jwt.token;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtConstants;
import io.jsonwebtoken.JwtException;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class RefreshToken extends Token {
    public static final String COOKIE_NAME = "refresh_token";

    public RefreshToken(String id, String subject) {
        super(id, subject);
    }

    public static RefreshToken create(String subject) {
        String id = ObjectId.get().toHexString();

        return new RefreshToken(id, subject);
    }

    public static RefreshToken decode(String token, IJwtDecoder decoder) throws JwtException {
        Token parsedToken = decoder.decodeToken(token, JwtConstants.JWT_RT_KEY);
        return (RefreshToken) parsedToken;
    }

    public String encode(IJwtEncoder encoder) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rti", getId());

        return encoder.encodeToken(claims, null, getSubject(), JwtConstants.JWT_RT_MAX_AGE, JwtConstants.JWT_RT_KEY);
    }
}
