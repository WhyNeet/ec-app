package dev.whyneet.ec_api.frameworks.data.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id()
    private String id;
    private String name;
    private String password;

    @Indexed(unique = true)
    private String email;

    private String address;
}
