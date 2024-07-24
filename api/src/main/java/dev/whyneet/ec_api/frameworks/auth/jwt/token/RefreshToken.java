package dev.whyneet.ec_api.frameworks.auth.jwt.token;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import io.jsonwebtoken.JwtException;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class RefreshToken extends Token {
    public static final String COOKIE_NAME = "refresh_token";

    public RefreshToken(String id, String subject) {
        super(id, subject);
    }

    public RefreshToken(String id, String subject, TokenAudience audience) {
        super(id, subject, audience);
    }

    public static RefreshToken create(String subject, TokenAudience tokenAudience) {
        String id = ObjectId.get().toHexString();

        return new RefreshToken(id, subject, tokenAudience);
    }

    public static RefreshToken decode(String token, IJwtDecoder decoder) throws JwtException {
        Token parsedToken = decoder.decodeToken(token, TokenType.REFRESH);
        return new RefreshToken(parsedToken.getId(), parsedToken.getSubject());
    }

    public String encode(IJwtEncoder encoder) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rti", getId());

        return encoder.encodeToken(claims, null, getSubject(), getAudience(), TokenType.REFRESH);
    }
}
