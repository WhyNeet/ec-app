package dev.whyneet.ec_api.core.abstracts.configuration;

import java.util.Optional;

public interface IApplicationDatabasePropertiesConfiguration {
    String prefix();
    String host();
    IApplicationDatabaseAuthPropertiesConfiguration auth();
    short port();

    interface IApplicationDatabaseAuthPropertiesConfiguration {
        String username();
        String password();
    }
}
