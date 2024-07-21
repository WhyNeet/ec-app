package dev.whyneet.ec_api.configuration;

import dev.whyneet.ec_api.frameworks.auth.jwt.filters.JwtAuthFilter;
import dev.whyneet.ec_api.frameworks.auth.jwt.filters.TokenRefreshFilter;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private TokenRefreshFilter tokenRefreshFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("**").permitAll())
                .logout((logout) -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/")
                        .deleteCookies(AccessToken.COOKIE_NAME, RefreshToken.COOKIE_NAME).permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(tokenRefreshFilter, JwtAuthFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 2, 19);
    }
}
