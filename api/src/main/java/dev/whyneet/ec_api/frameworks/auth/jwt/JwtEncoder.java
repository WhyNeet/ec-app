package dev.whyneet.ec_api.frameworks.auth.jwt;

import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtEncoder implements IJwtEncoder {
    @Override
    public String encodeToken(Map<String, Object> claims, String id, String subject, long maxAge, Key key) {
        Date iss = new Date(System.currentTimeMillis());

        Date exp = new Date(System.currentTimeMillis() + maxAge);

        return Jwts.builder().claims(claims).subject(subject).issuedAt(iss).expiration(exp).id(id).signWith(key).compact();
    }
}
