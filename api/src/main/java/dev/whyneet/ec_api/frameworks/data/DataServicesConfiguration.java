package dev.whyneet.ec_api.frameworks.data;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.frameworks.data.mongo.MongoDataServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataServicesConfiguration {
    @Bean
    public IDataServices dataServices() {
        return new MongoDataServices();
    }
}
