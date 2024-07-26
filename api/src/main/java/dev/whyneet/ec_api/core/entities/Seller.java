package dev.whyneet.ec_api.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "sellers")
public class Seller {
    @Id
    private String id;
    private String businessName;
    @Indexed(unique = true)
    private String businessShortName;
    private String taxId;
    private String password;
    private Boolean verified;
    private String description;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{ 'sellerId': ?#{#self._id} }")
    private List<Product> products;
}
