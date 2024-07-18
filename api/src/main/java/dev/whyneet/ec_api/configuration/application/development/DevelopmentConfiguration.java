package dev.whyneet.ec_api.configuration.application.development;

import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!production")
public class DevelopmentConfiguration implements IApplicationConfiguration {
    @Override
    public IApplicationDatabasesConfiguration databases() {
        return new DevelopmentDatabasesConfiguration();
    }
}
