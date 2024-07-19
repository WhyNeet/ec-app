package dev.whyneet.ec_api.core.abstracts.configuration;

import java.security.Key;

public interface IApplicationTokenPropertiesConfiguration {
    String secret();
    long maxAge();
}
