package dev.whyneet.ec_api.frameworks.auth.jwt;

import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtEncoder implements IJwtEncoder {
    @Autowired
    private IApplicationConfiguration configuration;

    @Override
    public String encodeToken(Map<String, Object> claims, String id, String subject, TokenType tokenType) {
        Date iss = new Date(System.currentTimeMillis());

        long maxAge = tokenType == TokenType.ACCESS ? configuration.tokens().accessToken()
                .maxAge() : configuration.tokens().refreshToken().maxAge();

        Date exp = new Date(System.currentTimeMillis() + maxAge * 1000);

        Key key = tokenType == TokenType.ACCESS ? KeyUtil.keyFromString(configuration.tokens().accessToken()
                .secret()) : KeyUtil.keyFromString(configuration.tokens().refreshToken().secret());

        return Jwts.builder().claims(claims).subject(subject).issuedAt(iss).expiration(exp).id(id).signWith(key)
                .compact();
    }
}
