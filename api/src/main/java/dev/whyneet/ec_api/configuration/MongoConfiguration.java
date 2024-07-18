package dev.whyneet.ec_api.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.whyneet.ec_api.core.abstracts.configuration.IApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {
    @Autowired
    private IApplicationConfiguration configuration;

    @Override
    protected String getDatabaseName() {
        return "ec-app";
    }

    @Override
    public MongoClient mongoClient() {
        String uri = String.format("%s://%s%s:%d", configuration.databases().mongo().prefix(), configuration.databases().mongo().host(), configuration.databases().mongo().password().map(password -> '@' + password).orElse(""), configuration.databases().mongo().port());
        ConnectionString connectionString = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();

        return MongoClients.create(mongoClientSettings);
    }
}
