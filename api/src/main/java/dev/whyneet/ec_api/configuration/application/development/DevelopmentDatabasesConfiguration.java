package dev.whyneet.ec_api.configuration.application.development;

import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasePropertiesConfiguration;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationDatabasesConfiguration;

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
        public IApplicationDatabaseAuthPropertiesConfiguration auth() {
            return new MongoAuthProperties();
        }

        @Override
        public short port() {
            return 27017;
        }

        public static class MongoAuthProperties implements IApplicationDatabaseAuthPropertiesConfiguration {
            @Override
            public String username() {
                return "root";
            }

            @Override
            public String password() {
                return "root";
            }
        }
    }

    @Override
    public IApplicationDatabasePropertiesConfiguration mongo() {
        return new DevelopmentDatabasesMongoConfiguration();
    }
}
