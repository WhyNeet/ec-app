package dev.whyneet.ec_api.frameworks.auth.jwt.filters;

import dev.whyneet.ec_api.frameworks.auth.jwt.TokenType;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.RefreshToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class FilterUtil {
    public static Optional<String> getToken(HttpServletRequest request, TokenType tokenType) {
        return request.getCookies() != null ? Arrays.stream(request.getCookies()).filter(cookie -> Objects.equals(cookie.getName(), tokenType == TokenType.ACCESS ? AccessToken.COOKIE_NAME : RefreshToken.COOKIE_NAME)).findFirst().map(Cookie::getValue) : Optional.empty();
    }
}
