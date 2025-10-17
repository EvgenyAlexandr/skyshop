package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope   //Для каждого пользователя отдельный бин
public class ProductBasket {

    private final Map<UUID, Integer > basket; // Map - Продукты в корзине.

    // Конструктор
    public ProductBasket() {
        basket = new HashMap<>();
    }

    // Метод подсчета продуктов в корзине
    public void addProduct(UUID id) {
        basket.merge(id, 1, Integer::sum) ;     // Если в Map значения "key" с таким id нет, то значение "value" 1, если есть Integer::sum (+1)
        System.out.println("Продукт добавлен в корзину");
    }

    // Метод, который печатает содержимое корзины
    public Map<UUID, Integer> getProductBasket() {
        return Collections.unmodifiableMap(basket); // Обёртка над исходной Map, которая блокирует операции модификации.
    }



}
