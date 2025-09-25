package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
//import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import java.awt.*;
import java.util.Collection;

@RestController             // аннотация Spring для создания REST API
public class ShopController {
//    private int count;
//
    // http://localhost:8080
    // http://127.0.0.1:8080
    @GetMapping             // GET запрос
    public String hello() {
        return "Привет, вроде все работает ...";
    }
//
//    // http://localhost:8080/counter
//    @GetMapping("/counter")
//    public String count() {
//        this.count++;
//        return "Количество запросов: " + this.count;
//    }


    private final StorageService storageService;
    private final SearchService searchService;
    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return storageService.getAllProduct();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticle();
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }


}
