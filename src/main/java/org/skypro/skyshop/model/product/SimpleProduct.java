package org.skypro.skyshop.model.product;

import java.util.UUID;

/// Простой продукт
public class SimpleProduct extends Product {
    private final int price;    //Цена

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name);
        if (price < 1) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + "<" + price + ">";
    }
}