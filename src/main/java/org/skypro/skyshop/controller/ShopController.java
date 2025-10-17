package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController             // аннотация Spring для создания REST API
public class ShopController {

    private final StorageService storageService;    // Сервис Хранения
    private final SearchService  searchService;     // Поисковый Сервис

    @Autowired
    private       BasketService  basketService;     // Сервис Корзины

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    // http://localhost:8080
    // http://127.0.0.1:8080
    @GetMapping             // GET запрос
    public String hello() {
        return "Привет, вроде все работает ...";
    }

    //localhost:8080/products
    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return storageService.getAllProduct();
    }

    //localhost:8080/articles
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getAllArticle();
    }

    //localhost:8080/search
    //localhost:8080/search?pattern=<какая-то строка>
    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam("pattern") String pattern) {
        return searchService.search(pattern);
    }

    // Добавление продуктов в корзину
    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addToBasket(id);
        return "*Продукт успешно добавлен*";
    }

    // Отображение содержимого корзины
    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }


}
