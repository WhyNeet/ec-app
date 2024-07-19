package dev.whyneet.ec_api.frameworks.auth.jwt.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenPair {
    private AccessToken accessToken;
    private RefreshToken refreshToken;
}
