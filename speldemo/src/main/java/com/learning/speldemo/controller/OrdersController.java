package com.learning.speldemo.controller;


import com.learning.speldemo.data.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final Order order;
    private final Shipping shipping;
    private final City city;
    private final User user;

    public OrdersController(User user, Order order, Shipping shipping, City city) {
        this.user = user;
        this.order = order;
        this.shipping = shipping;
        this.city = city;
    }

    @GetMapping("/customer")
    public ResponseEntity<String> getCustomer() {
        return ResponseEntity.ok(user.getName());
    }

    @GetMapping("/amount")
    public ResponseEntity<Double> getOrderAmount() {
        return ResponseEntity.ok(order.getAmount());
    }

    @GetMapping("/discount")
    public ResponseEntity<Double> getOrderDiscount() {
        return ResponseEntity.ok(order.getDiscount());
    }

    @GetMapping("/amount/formatted")
    public ResponseEntity<String> getOrderFormattedAmount() {
        return ResponseEntity.ok(order.getFormattedAmount());
    }

    @GetMapping("/summary")
    public ResponseEntity<String> getOrderSummary() {
        return ResponseEntity.ok(order.getOrderSummary());
    }

    @GetMapping("/shipping/locations")
    public ResponseEntity<List<City>> getShippingLocations() {
        return ResponseEntity.ok(order.getShippingLocations());
    }

    @GetMapping("/shipping/western")
    public ResponseEntity<Map<String, List<City>>> getWesternShippingLocations() {
        return ResponseEntity.ok(order.getWesternShippingLocations());
    }

    @GetMapping("/shipping/noOfCheap")
    public ResponseEntity<Integer> getNoOfCheapShippingLocations() {
        return ResponseEntity.ok(order.getNoOfCheapShippingLocations());
    }

    @GetMapping("/shipping/nonCapital")
    public ResponseEntity<List<City>> getNonCapitalShippingLocations() {
        return ResponseEntity.ok(order.getNonCapitalShippingLocations());
    }
}
