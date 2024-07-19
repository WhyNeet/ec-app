package dev.whyneet.ec_api.features.token;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.RefreshToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private IDataRepository<Token, String> tokenRepository;
    @Autowired
    private IJwtEncoder jwtEncoder;

    public TokenPair generateTokenPair(String userId, String oldTokenId) {
        RefreshToken refreshToken = oldTokenId == null ? RefreshToken.create(userId) : new RefreshToken(oldTokenId, userId);
        AccessToken accessToken = AccessToken.create(refreshToken);

        tokenRepository.save(refreshToken);

        return new TokenPair(accessToken, refreshToken);
    }

    public void setCookies(HttpServletResponse response, TokenPair tokenPair) {
        String refreshToken = tokenPair.getRefreshToken().encode(jwtEncoder);
        String accessToken = tokenPair.getAccessToken().encode(jwtEncoder);

        Cookie refreshTokenCookie = new Cookie(RefreshToken.COOKIE_NAME, refreshToken);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);
        Cookie accessTokenCookie = new Cookie(AccessToken.COOKIE_NAME, accessToken);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);

        response.addCookie(refreshTokenCookie);
        response.addCookie(accessTokenCookie);
    }
}
