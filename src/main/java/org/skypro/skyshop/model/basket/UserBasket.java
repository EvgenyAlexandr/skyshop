package org.skypro.skyshop.model.basket;

import java.util.List;


public final class UserBasket {
    private final List<BasketItem> itemsList;   // Лист продуктов
    private final int total;                    // Общая цена

    // Конструктор
    public UserBasket(List<BasketItem> itemsList) {
        this.itemsList = itemsList;
        this.total = itemsList.stream()
                .mapToInt(BasketItem -> BasketItem.getProduct().getPrice() * BasketItem.getCount())
                .sum();
    }

    public List<BasketItem> getItemsList() {
        return itemsList;
    }

    public int getTotal() {
        return total;
    }
}
