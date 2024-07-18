package dev.whyneet.ec_api.configuration.application.development;

import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasePropertiesConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasesConfiguration;

import java.util.Optional;

public class DevelopmentDatabasesConfiguration implements IApplicationDatabasesConfiguration {
    public static class DevelopmentDatabasesMongoConfiguration implements IApplicationDatabasePropertiesConfiguration {
        @Override
        public String prefix() {
            return "mongodb";
        }

        @Override
        public String host() {
            return "localhost";
        }

        @Override
        public Optional<String> password() {
            return Optional.empty();
        }

        @Override
        public short port() {
            return 27017;
        }
    }

    @Override
    public IApplicationDatabasePropertiesConfiguration mongo() {
        return new DevelopmentDatabasesMongoConfiguration();
    }
}
