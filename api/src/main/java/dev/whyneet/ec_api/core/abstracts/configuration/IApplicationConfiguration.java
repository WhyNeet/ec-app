package dev.whyneet.ec_api.core.abstracts.configuration;

public interface IApplicationConfiguration {
    IApplicationDatabasesConfiguration databases();
    IApplicationTokensConfiguration tokens();
    IApplicationPaymentsConfiguration payments();
}
