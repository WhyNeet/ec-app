package dev.whyneet.ec_api.core.entities;

import io.jsonwebtoken.Claims;
import lombok.Data;

import java.util.Date;
import java.util.function.Function;

@Data
public class Token {
    private Claims claims;
    private String id;
    private String subject;

    public Token(Claims claims) {
        this.claims = claims;
        this.id = isRefreshToken() ? extractClaim(c -> c.get("rti", String.class)) : extractClaim(Claims::getId);
        this.subject = extractClaim(Claims::getSubject);
    }

    public Token(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public boolean isExpired() {
        return extractClaim(Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(Function<Claims, T> resolver) {
        return resolver.apply(claims);
    }

    public boolean isRefreshToken() {
        return claims.containsValue("rti");
    }
}
