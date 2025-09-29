package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE_PRODUCT = 100; // Фиксированная цена

    // Конструктор
    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    // Метод - Проверка товар с фиксированной ценой
    @Override
    public boolean isSpecial() {
        return true;
    }

    // Метод - Возвращает фиксированную цену.
    @Override
    public int getPrice() {
        return FIX_PRICE_PRODUCT;
    }

    // Строковое представление объекта
    @Override
    public String toString() {
        return super.toString() + "Фиксированная цена <" + FIX_PRICE_PRODUCT + ">";
    }

}
