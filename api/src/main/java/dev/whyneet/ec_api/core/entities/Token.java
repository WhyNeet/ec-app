package dev.whyneet.ec_api.core.entities;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.function.Function;

@Data
@NoArgsConstructor
@Document(collection = "tokens")
public class Token {
    @Id
    private String id;
    @Transient
    private Claims claims;
    @Transient
    private String subject;
    @Transient
    private TokenAudience audience;

    public Token(Claims claims) {
        this.claims = claims;
        this.id = isRefreshToken() ? extractClaim(c -> c.get("rti", String.class)) : extractClaim(Claims::getId);
        this.subject = extractClaim(Claims::getSubject);

        var audClaim = extractClaim(Claims::getAudience).stream().findFirst();
        this.audience = audClaim.map(TokenAudience::valueOf).orElse(TokenAudience.User);
    }

    public Token(String id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Token(String id, String subject, TokenAudience audience) {
        this.id = id;
        this.subject = subject;
        this.audience = audience;
    }

    public boolean isExpired() {
        return extractClaim(Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(Function<Claims, T> resolver) {
        return resolver.apply(claims);
    }

    public boolean isRefreshToken() {
        return claims.containsKey("rti");
    }
}
