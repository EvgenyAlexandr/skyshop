package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int baseAmount;   // Базовая цена
    private final int discount;     // Скидка

    public DiscountedProduct(UUID id, String name, int baseAmount, int discount) {
        super(id, name);
        if (baseAmount < 1) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }

        this.baseAmount = baseAmount;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Некорректное значение.Скидка может быть в интервале от 0 до 100");
        }
        this.discount = discount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getPrice() {
        return baseAmount - baseAmount * discount / 100;
    }

    @Override
    public String toString() {
        return super.toString() + "<" + getPrice() + "> (<" + discount + ">%)";
    }

}
