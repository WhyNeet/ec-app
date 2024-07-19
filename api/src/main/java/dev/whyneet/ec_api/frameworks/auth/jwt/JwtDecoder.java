package dev.whyneet.ec_api.frameworks.auth.jwt;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import dev.whyneet.ec_api.core.entities.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import java.security.Key;

public class JwtDecoder implements IJwtDecoder {
    @Autowired
    private IApplicationConfiguration configuration;

    @Override
    public Token decodeToken(String token, TokenType tokenType) throws JwtException {
        Key key = tokenType == TokenType.ACCESS ? KeyUtil.keyFromString(configuration.tokens().accessToken()
                .secret()) : KeyUtil.keyFromString(configuration.tokens().refreshToken().secret());

        Claims claims = Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
        return new Token(claims);
    }
}
