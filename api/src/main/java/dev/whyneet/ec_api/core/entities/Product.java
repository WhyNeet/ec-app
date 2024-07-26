package dev.whyneet.ec_api.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private List<String> images;
    private Integer price;
    private String description;

    private String sellerId;
    @DocumentReference(lazy = true)
    private Seller seller;
}
