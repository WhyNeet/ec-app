package dev.whyneet.ec_api.frameworks.auth.jwt.filters;

import dev.whyneet.ec_api.core.abstracts.IJwtDecoder;
import dev.whyneet.ec_api.core.entities.TokenAudience;
import dev.whyneet.ec_api.features.token.TokenService;
import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.RefreshToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Order(0)
@Component
public class TokenRefreshFilter extends OncePerRequestFilter {
    @Autowired
    private IJwtDecoder jwtDecoder;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> accessTokenString = FilterUtil.getToken(request, TokenType.ACCESS);
        Optional<String> tokenString = FilterUtil.getToken(request, TokenType.REFRESH);

        if (tokenString.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        // initially, only refresh access token when its cookie is empty
        boolean doRefresh = accessTokenString.isEmpty();

        if (!doRefresh) {
            try {
                AccessToken.decode(accessTokenString.get(), jwtDecoder);
            } catch (ExpiredJwtException ex) {
                doRefresh = true;
            }
        }

        if (!doRefresh) {
            filterChain.doFilter(request, response);
            return;
        }

        RefreshToken refreshToken;

        try {
            refreshToken = RefreshToken.decode(tokenString.get(), jwtDecoder);
        } catch (Exception ex) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!tokenService.tokenExists(refreshToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        TokenPair tokenPair = tokenService.generateTokenPair(refreshToken.getAudience() == null ? TokenAudience.User : refreshToken.getAudience(), refreshToken.getSubject(), refreshToken.getId());
        Cookie accessTokenCookie = tokenService.getAccessTokenCookie(tokenPair.getAccessToken());
        Cookie refreshTokenCookie = tokenService.getRefreshTokenCookie(tokenPair.getRefreshToken());

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        CustomizableHttpServletRequestWrapper customizableHttpServletRequestWrapper = new CustomizableHttpServletRequestWrapper(request);
        customizableHttpServletRequestWrapper.replaceCookie(accessTokenCookie);
        customizableHttpServletRequestWrapper.replaceCookie(refreshTokenCookie);


        filterChain.doFilter(customizableHttpServletRequestWrapper, response);
    }
}
