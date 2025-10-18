package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket  productBasket;     // Содержимое корзины
    private final StorageService storageService;    // Поисковый сервис

    // Конструктор
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Метод - Метод добавления товара в корзину по id
    public void addToBasket(UUID id) {
        if (storageService.getProductById(id).isPresent() == false) {
            throw new NoSuchProductException("Продукта с данным ID не существует: " + id);
        } else {
            productBasket.addProduct(id);
        }
    }

    // Метод отображения корзины пользователю
    public UserBasket getUserBasket() {
        List<BasketItem> tempBasketItemList = productBasket.getProductBasket().entrySet().stream()
                .map(m -> new BasketItem(storageService.getProductById(m.getKey()).orElseThrow(() -> new NoSuchProductException("Продукта с данным ID не существует: " + m.getKey())),
                        m.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        UserBasket tempUserBasket = new UserBasket(tempBasketItemList);
        return tempUserBasket;
    }

}