package dev.whyneet.ec_api.frameworks.auth.jwt;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.entities.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;

public class JwtDecoder implements IJwtDecoder {
    @Override
    public Token decodeToken(String token, Key key) {
        Claims claims = Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
        return new Token(claims);
    }
}
