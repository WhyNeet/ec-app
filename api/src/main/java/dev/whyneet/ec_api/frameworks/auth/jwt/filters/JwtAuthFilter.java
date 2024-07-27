package dev.whyneet.ec_api.frameworks.auth.jwt.filters;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.entities.Seller;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.seller.SellerService;
import dev.whyneet.ec_api.features.token.TokenService;
import dev.whyneet.ec_api.features.user.UserService;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Order(1)
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private IJwtDecoder decoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenString = FilterUtil.getToken(request, TokenType.ACCESS);

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

        if (!tokenService.tokenExists(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Object> authorizedEntity;

        if (token.getAudience() == TokenAudience.Seller) {
            Optional<Seller> seller = sellerService.getSellerById(token.getSubject());
            authorizedEntity = seller.map(s -> s);
        } else {
            Optional<User> user = userService.getUserById(token.getSubject());
            authorizedEntity = user.map(u -> u);
        }


        if (authorizedEntity.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken.authenticated(authorizedEntity.get(), null, new ArrayList<>());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}
