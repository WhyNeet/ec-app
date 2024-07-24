package dev.whyneet.ec_api.features.token;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.abstracts.IJwtEncoder;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.core.entities.TokenAudience;
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
    private IDataServices dataServices;
    @Autowired
    private IJwtEncoder jwtEncoder;

    public TokenPair generateTokenPair(TokenAudience tokenAudience, String entityId, String oldTokenId) {
        if (oldTokenId != null) dataServices.tokens().deleteById(oldTokenId);

        RefreshToken refreshToken = RefreshToken.create(entityId, tokenAudience);
        AccessToken accessToken = AccessToken.create(refreshToken);

        dataServices.tokens().save(refreshToken);

        return new TokenPair(accessToken, refreshToken);
    }

    public Cookie getAccessTokenCookie(AccessToken token) {
        String accessToken = token.encode(jwtEncoder);

        Cookie accessTokenCookie = new Cookie(AccessToken.COOKIE_NAME, accessToken);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);

        return accessTokenCookie;
    }

    public Cookie getRefreshTokenCookie(RefreshToken token) {
        String refreshToken = token.encode(jwtEncoder);

        Cookie refreshTokenCookie = new Cookie(RefreshToken.COOKIE_NAME, refreshToken);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true);

        return refreshTokenCookie;
    }

    public void setCookies(HttpServletResponse response, TokenPair tokenPair) {
        Cookie accessTokenCookie = getAccessTokenCookie(tokenPair.getAccessToken());
        Cookie refreshTokenCookie = getRefreshTokenCookie(tokenPair.getRefreshToken());

        response.addCookie(refreshTokenCookie);
        response.addCookie(accessTokenCookie);
    }

    public boolean tokenExists(Token token) {
        return dataServices.tokens().getById(token.getId()).isPresent();
    }
}
