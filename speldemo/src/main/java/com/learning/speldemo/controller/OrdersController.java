package com.learning.speldemo.controller;


import com.learning.speldemo.data.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    final Order order;
    final User user;

    public OrdersController(User user, Order order) {
        this.user = user;
        this.order = order;
    }

    @GetMapping("/customer")
    public ResponseEntity<String> getCustomer() {
        return ResponseEntity.ok(user.getName());
    }

    @GetMapping("/amount")
    public ResponseEntity<Double> getOrderAmount() {
        return ResponseEntity.ok(order.getAmount());
    }
}
