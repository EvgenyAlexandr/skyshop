package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


import java.util.*;

@Component
@SessionScope //Для каздого пользователя отдельный бин
public class ProductBasket {

    private final Map<String, List<Product>> basket; // Map - Продукты в корзине.
    private final Map<UUID, Integer > basketV2; // Map - Продукты в корзине.

    // Конструктор
    public ProductBasket() {
        basket = new HashMap<>();
        basketV2 = new HashMap<>();

    }

    public void addProductV2(UUID id) {
        //String productName = product.getName();
        basketV2.merge(id, 1, Integer::sum) ;
        System.out.println("Продукт добавлен в корзину");
    }


    // Метод, который печатает содержимое корзины
    public Map<UUID, Integer> getProductBasket() {
        // ///
        return Collections.unmodifiableMap(basketV2);
    }


///////////////////////////////////////////////////////////




    // Метод добавления продукта в корзину
    public void addProduct(Product product) {
        String productName = product.getName();
        basket.computeIfAbsent(productName, k -> new ArrayList<>()).add(product);
        System.out.println("Продукт добавлен в корзину");
    }




    // Метод получения общей стоимости корзины
    public double getTotalCost() {
        return basket.values().stream()
                .flatMap(Collection::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }


    // Метод, который печатает содержимое корзины
    public void printBacket() {
        System.out.println("\nСодержимое корзины:");

        basket.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        System.out.println("Итого: " + getTotalCost());

        // Вызываем метод - Подсчета количества товаров специального типа.
        int specialCount = getCountSpecialProduct();
        System.out.println("Специальных товаров: " + specialCount);
    }



    // Метод, проверяющий продукт в корзине по имени
    public boolean containsProduct(String name) {
        return basket.containsKey(name);
    }


    // Метод, Очистки корзины
    public void clearBasket() {
        basket.clear();
        System.out.println("Корзина очищена");
    }


    // Метод, подсчета количества товаров специального типа.
    private int getCountSpecialProduct() {
        return (int) basket.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }


    // Метод удаления продукта по имени из корзины
    public List<Product> removeProduct(String name) {
        List<Product> removedProduct = basket.remove(name);

        System.out.println("Из корзины удалены товары:");

        if (removedProduct == null || removedProduct.isEmpty()) {
            System.out.println("Список пуст");
            return new ArrayList<>();
        } else {
            // Отображаем список удаляемых товаров.
            removedProduct.forEach(System.out::println);
        }
        return removedProduct;
    }
}