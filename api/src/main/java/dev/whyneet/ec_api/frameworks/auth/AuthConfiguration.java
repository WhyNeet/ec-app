package dev.whyneet.ec_api.frameworks.auth;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtDecoder;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {
    @Bean
    public IJwtEncoder getJwtEncoder() {
        return new JwtEncoder();
    }

    @Bean
    public IJwtDecoder getJwtDecoder() {
        return new JwtDecoder();
    }
}
