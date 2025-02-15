package dev.whyneet.ec_api.features.order;

import dev.whyneet.ec_api.core.abstracts.IDataServices;
import dev.whyneet.ec_api.core.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderFactory orderFactory;
    @Autowired
    private IDataServices dataServices;

    public Order createOrder(String productId, String userId, int quantity) {
        Order order = orderFactory.fromParams(productId, userId, quantity);

        return dataServices.orders().save(order);
    }

    public List<Order> getUserOrders(String userId) {
        return dataServices.orders().findAll(Example.of(new Order(null, null, userId, null)));
    }
}
