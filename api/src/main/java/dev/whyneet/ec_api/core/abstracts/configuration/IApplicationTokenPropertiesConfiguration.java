package dev.whyneet.ec_api.core.abstracts.configuration;

public interface IApplicationTokenPropertiesConfiguration {
    String secret();
    long maxAge();
}
