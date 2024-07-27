package dev.whyneet.ec_api.configuration.application.development;

import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationPaymentsConfiguration;

public class DevelopmentPaymentsConfiguration implements IApplicationPaymentsConfiguration {
    @Override
    public String apiKey() {
        return System.getenv("STRIPE_API_KEY");
    }
}
