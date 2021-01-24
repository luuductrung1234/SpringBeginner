package com.learning.conference.conferencefirstbootapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
    @Value("${app.version}")
    private String appVersion;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping
    @RequestMapping("/status")
    public ResponseEntity<Map> getStatus() {
        return ResponseEntity.ok(new HashMap<String, String>(){{
            put("app-version", appVersion);
            put("active-profile", activeProfile);
        }});
    }
}
