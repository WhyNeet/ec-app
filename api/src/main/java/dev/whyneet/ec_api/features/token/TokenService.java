package dev.whyneet.ec_api.features.token;

import dev.whyneet.ec_api.core.abstracts.IDataRepository;
import dev.whyneet.ec_api.core.entities.Token;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.AccessToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.RefreshToken;
import dev.whyneet.ec_api.frameworks.auth.jwt.token.TokenPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    @Autowired
    private IDataRepository<Token, String> tokenRepository;

    public TokenPair generateTokenPair(String userId, String oldTokenId) {
        RefreshToken refreshToken = oldTokenId == null ? RefreshToken.create(userId) : new RefreshToken(oldTokenId, userId);
        AccessToken accessToken = AccessToken.create(refreshToken);

        tokenRepository.save(refreshToken);

        return new TokenPair(accessToken, refreshToken);
    }
}
