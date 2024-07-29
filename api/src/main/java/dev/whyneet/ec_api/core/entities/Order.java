package dev.whyneet.ec_api.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String productId;
    private Integer quantity;
    private String userId;
}
