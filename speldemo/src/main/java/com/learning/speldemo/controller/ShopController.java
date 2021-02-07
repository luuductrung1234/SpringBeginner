package com.learning.speldemo.controller;

import com.learning.speldemo.data.Shop;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops")
public class ShopController {
    private final Shop shop;

    public ShopController(Shop shop) {
        this.shop = shop;
    }

    @GetMapping("/name")
    public ResponseEntity<String> getCustomer() {
        return ResponseEntity.ok(shop.getName());
    }
}
