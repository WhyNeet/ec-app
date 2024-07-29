package dev.whyneet.ec_api.features.order;

import dev.whyneet.ec_api.core.dtos.order.OrderDto;
import dev.whyneet.ec_api.core.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getProductId(), order.getUserId(), order.getQuantity());
    }

    public Order fromParams(String productId, String userId, int quantity) {
        return new Order(null, productId, userId, quantity);
    }
}
