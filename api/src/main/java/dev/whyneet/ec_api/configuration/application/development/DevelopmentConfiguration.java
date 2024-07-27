package dev.whyneet.ec_api.configuration.application.development;

import com.stripe.Stripe;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasesConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationPaymentsConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationTokensConfiguration;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!production")
public class DevelopmentConfiguration implements IApplicationConfiguration {
    @PostConstruct
    private void postConstruct() {
        Stripe.apiKey = payments().apiKey();
    }

    @Override
    public IApplicationDatabasesConfiguration databases() {
        return new DevelopmentDatabasesConfiguration();
    }

    @Override
    public IApplicationTokensConfiguration tokens() {
        return new DevelopmentTokensConfiguration();
    }

    @Override
    public IApplicationPaymentsConfiguration payments() {
        return new DevelopmentPaymentsConfiguration();
    }
}
