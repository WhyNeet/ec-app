package dev.whyneet.ec_api.controllers;

import dev.whyneet.ec_api.core.dtos.order.OrderDto;
import dev.whyneet.ec_api.core.entities.User;
import dev.whyneet.ec_api.features.order.OrderFactory;
import dev.whyneet.ec_api.features.order.OrderService;
import dev.whyneet.ec_api.frameworks.auth.validation.RequireAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFactory orderFactory;

    @RequireAuthentication
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getUserOrders(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.getUserOrders(user.getId()).stream().map(orderFactory::toDto)
                .collect(Collectors.toList()));
    }
}
