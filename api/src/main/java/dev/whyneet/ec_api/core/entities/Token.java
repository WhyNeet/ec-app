package dev.whyneet.ec_api.core.entities;

import io.jsonwebtoken.Claims;
import lombok.Data;

import java.util.function.Function;

@Data
public class Token {
    private String id;
    private String subject;

    public Token(Claims claims) {
        this.id = isRefreshToken(claims) ? extractClaim(claims, c -> c.get("rti", String.class)) : extractClaim(claims, Claims::getId);
        this.subject = extractClaim(claims, Claims::getSubject);
    }

    public Token(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public <T> T extractClaim(Claims claims, Function<Claims, T> resolver) {
        return resolver.apply(claims);
    }

    public boolean isRefreshToken(Claims claims) {
        return claims.containsValue("rti");
    }
}
