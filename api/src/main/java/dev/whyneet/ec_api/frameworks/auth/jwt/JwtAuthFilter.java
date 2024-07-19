package dev.whyneet.ec_api.frameworks.auth.jwt;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.user.UserService;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private IJwtDecoder decoder;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenString = getToken(request);

        if (tokenString.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        AccessToken token;

        try {
            token = AccessToken.decode(tokenString.get(), decoder);
        } catch (Exception ex) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<User> user = userService.getUserById(token.getSubject());

        if (user.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken.authenticated(user.get(), null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        System.out.println("user authenticated: " + user.get().getName());

        filterChain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request) {
        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String cookieToken = request.getCookies() != null ? Arrays.stream(request.getCookies()).filter(cookie -> Objects.equals(cookie.getName(), AccessToken.COOKIE_NAME)).findFirst().map(Cookie::getValue).orElse(null) : null;

        return trimTokenString(cookieToken, headerToken);
    }

    private Optional<String> trimTokenString(String cookieToken, String headerToken) {
        // if there is no Bearer token in the header, return the one from cookie
        if (headerToken == null) return Optional.ofNullable(cookieToken);
        // otherwise return the Bearer token without "Bearer " prefix
        return Optional.of(headerToken.substring(7));
    }
}
