package dev.whyneet.ec_api.configuration.application.development;

import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationTokenPropertiesConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationTokensConfiguration;
import lombok.AllArgsConstructor;

public class DevelopmentTokensConfiguration implements IApplicationTokensConfiguration {
    @AllArgsConstructor
    public static class DevelopmentTokenPropertiesConfiguration implements IApplicationTokenPropertiesConfiguration {
        private String secret;
        private long maxAge;

        @Override
        public String secret() {
            return secret;
        }

        @Override
        public long maxAge() {
            return maxAge;
        }
    }

    @Override
    public IApplicationTokenPropertiesConfiguration accessToken() {
        return new DevelopmentTokenPropertiesConfiguration("b4/46CGBjeTYGBJN8UAVTvU1CNb2MIelQRHshFqNRig=", 120);
    }

    @Override
    public IApplicationTokenPropertiesConfiguration refreshToken() {
        return new DevelopmentTokenPropertiesConfiguration("UTAHQ3wN2v1wz0NyTgPN51K4mCg+Ck5v2QGnUx+pyIA=", 60 * 60 * 24 * 120);
    }
}
