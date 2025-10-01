package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;


@Service
public class StorageService {
    private final Map<UUID, Product> productMap = new HashMap<>();
    private final Map<UUID, Article> articleMap = new HashMap<>();

    public StorageService() {
        addPoduct();
        addArticle();
    }

    public Collection<Product> getAllProduct() {
        return new ArrayList<>(productMap.values());
    }

    public Collection<Article> getAllArticle() {
        return new ArrayList<>(articleMap.values());
    }

    // Метод - Добавляем продукты
    private void addPoduct() {
        // Простой продукт
        SimpleProduct sausage   = new SimpleProduct(UUID.randomUUID(), "Сосиски", 200);
        SimpleProduct bread     = new SimpleProduct(UUID.randomUUID(), "Батон",   54);
        // Продукты с фиксированной ценой
        FixPriceProduct meat    = new FixPriceProduct(UUID.randomUUID(), "Говядина");
        FixPriceProduct milk    = new FixPriceProduct(UUID.randomUUID(), "Молоко");
        // Товары со скидкой
        Product bananas         = new DiscountedProduct(UUID.randomUUID(), "Бананы",    200, 50);
        Product potato          = new DiscountedProduct(UUID.randomUUID(), "Картофель", 75,  10);
        Product mandarin        = new DiscountedProduct(UUID.randomUUID(), "Мандарины", 150, 10);
        Product apples          = new DiscountedProduct(UUID.randomUUID(), "Яблоки",    80,  10);

        productMap.put(sausage.getId(),     sausage);
        productMap.put(bread.getId(),       bread);
        productMap.put(meat.getId(),            meat);
        productMap.put(milk.getId(),            milk);
        productMap.put(bananas.getId(),            bananas);
        productMap.put(potato.getId(),             potato);
        productMap.put(mandarin.getId(),           mandarin);
        productMap.put(apples.getId(),             apples);
    }

    // Метод - Добавляем Артикул
    private void addArticle() {
        Article sausageArticle  = new Article(UUID.randomUUID(), "Сосиски", "Это колбасные изделия из измельченного мяса животных или птиц, прошедшие специальную термическую обработку.");
        Article breadArticle    = new Article(UUID.randomUUID(), "Батон", "Распространённый вид булочных изделий, характеризующийся вытянутой формой и особой технологией приготовления.");
        Article bananasArticle  = new Article(UUID.randomUUID(), "Банан", "Многолетнее травянистое растение семейства Банановые, одна из важнейших пищевых культур тропических стран.");
        Article potatoArticle   = new Article(UUID.randomUUID(), "Картофель", "Многолетнее клубненосное растение семейства Паслёновые, важнейшая пищевая культура мирового значения.");
        Article mandarinArticle = new Article(UUID.randomUUID(), "Мандарин", "Вид растений рода цитрус, важная плодовая культура семейства рутовых.");
        Article applesArticle   = new Article(UUID.randomUUID(), "Яблоко", "Сочный, плод яблони, один из самых распространённых и популярных фруктов в мире.");

        articleMap.put(sausageArticle.getId(),   sausageArticle);
        articleMap.put(breadArticle.getId(),     breadArticle);
        articleMap.put(bananasArticle.getId(),   bananasArticle);
        articleMap.put(potatoArticle.getId(),    potatoArticle);
        articleMap.put(mandarinArticle.getId(),  mandarinArticle);
        articleMap.put(applesArticle.getId(),    applesArticle);
    }
}