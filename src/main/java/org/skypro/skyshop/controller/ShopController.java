package org.skypro.skyshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private int count;

    // http://localhost:8080
    @GetMapping
    public String hello() {
        return "Hello, world!";
    }

    // http://localhost:8080/counter
    @GetMapping("/counter")
    public String count() {
        this.count++;
        return "Количество запросов: " + this.count;
    }


}
