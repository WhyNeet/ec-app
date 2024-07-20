package dev.whyneet.ec_api.frameworks.auth;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtDecoder;
import dev.whyneet.ec_api.frameworks.auth.jwt.JwtEncoder;
import dev.whyneet.ec_api.frameworks.auth.jwt.filters.TokenRefreshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AuthConfiguration {
    private TokenRefreshFilter tokenRefreshFilter;

    @Bean
    public IJwtEncoder getJwtEncoder() {
        return new JwtEncoder();
    }

    @Bean
    public IJwtDecoder getJwtDecoder() {
        return new JwtDecoder();
    }

    @Bean
    @DependsOn("tokenRefreshFilter")
    public FilterRegistrationBean<TokenRefreshFilter> tokenRefreshFilterRegistrationBean() {
        FilterRegistrationBean<TokenRefreshFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tokenRefreshFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public TokenRefreshFilter tokenRefreshFilter() {
        this.tokenRefreshFilter = new TokenRefreshFilter();

        return tokenRefreshFilter;
    }
}
