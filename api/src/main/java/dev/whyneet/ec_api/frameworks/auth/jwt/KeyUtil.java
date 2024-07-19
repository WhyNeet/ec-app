package dev.whyneet.ec_api.frameworks.auth.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class KeyUtil {
    public static Key keyFromString(String src) {
        byte[] bytes = Decoders.BASE64.decode(src);
        return Keys.hmacShaKeyFor(bytes);
    }
}
