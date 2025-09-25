package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE_PRODUCT = 100; // Фиксированная цена

    public FixPriceProduct(UUID id, String name) {
        super(id, name);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int getPrice() {
        return FIX_PRICE_PRODUCT;
    }

    @Override
    public String toString() {
        return super.toString() + "Фиксированная цена <" + FIX_PRICE_PRODUCT + ">";
    }

}
