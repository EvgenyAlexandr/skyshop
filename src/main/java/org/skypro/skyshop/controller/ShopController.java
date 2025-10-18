package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
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
    private final BasketService  basketService;     // Сервис Корзины

    // Конструктор
    @Autowired
    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService  = searchService;
        this.basketService  = basketService;
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
        Collection<SearchResult> searchResults = searchService.search(pattern);
        if (searchResults.isEmpty()) {
            throw new NoSuchProductException("Продукт с таким ID не найден");
        }
        return searchService.search(pattern);
    }

    // Добавление продуктов в корзину       http://localhost:8080/basket/d648c957-cad1-4b84-b953-62849f7a3806
    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addToBasket(id);
        return "*Продукт успешно добавлен*";
    }

    // Отображение содержимого корзины      http://localhost:8080/basket
    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }


}
