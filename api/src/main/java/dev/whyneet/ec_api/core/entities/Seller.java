package dev.whyneet.ec_api.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "sellers")
public class Seller {
    @Id
    private String id;
    @Indexed(unique = true)
    private String businessName;
    @Indexed(unique = true)
    private String businessShortName;
    private String taxId;
    private String password;
    private boolean verified;
    private String description;
}
