package dev.whyneet.ec_api.frameworks.auth.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtConstants {
    // 120 seconds
    public static final byte JWT_AT_MAX_AGE = 120;
    // 120 days
    public static final long JWT_RT_MAX_AGE = 60 * 60 * 24 * 120;

    public static final Key JWT_AT_KEY = getKey(System.getenv("tokens.access.key"));
    public static final Key JWT_RT_KEY = getKey(System.getenv("tokens.refresh.key"));

    private static Key getKey(String src) {
        byte[] bytes = Decoders.BASE64.decode(src);
        return Keys.hmacShaKeyFor(bytes);
    }
}
