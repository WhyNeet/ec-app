package dev.whyneet.ec_api.core.abstracts.configuration;

import java.util.Optional;

public interface IApplicationDatabasePropertiesConfiguration {
    String prefix();
    String host();
    Optional<String> password();
    short port();
}
