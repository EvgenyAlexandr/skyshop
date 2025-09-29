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

    private void addPoduct() {
        SimpleProduct sausage   = new SimpleProduct(UUID.randomUUID(), "Колбаса", 20);
        SimpleProduct bread     = new SimpleProduct(UUID.randomUUID(), "Хлеб", 6);
        FixPriceProduct meat    = new FixPriceProduct(UUID.randomUUID(), "Мясо");
        FixPriceProduct beer    = new FixPriceProduct(UUID.randomUUID(), "Пиво");
        Product tomatoes        = new DiscountedProduct(UUID.randomUUID(), "Помидоры", 12, 50);
        Product potato          = new DiscountedProduct(UUID.randomUUID(), "Картофель", 45, 10);
        Product vegetableFirst  = new DiscountedProduct(UUID.randomUUID(), "овощь", 45, 10);
        Product vegetableSecond = new DiscountedProduct(UUID.randomUUID(), "яблоко", 45, 10);

        productMap.put(sausage.getId(), sausage);
        productMap.put(bread.getId(), bread);
        productMap.put(meat.getId(), meat);
        productMap.put(beer.getId(), beer);
        productMap.put(tomatoes.getId(), tomatoes);
        productMap.put(potato.getId(), potato);
        productMap.put(vegetableFirst.getId(), vegetableFirst);
        productMap.put(vegetableSecond.getId(), vegetableSecond);
    }

    private void addArticle() {
        Article tomatoesTheBest = new Article(UUID.randomUUID(), "Томаты", "Помидоры лучшие овощи");
        Article potatoTheBest   = new Article(UUID.randomUUID(), "Картофель", "Картофель хороший овощь");
        Article beerArticle     = new Article(UUID.randomUUID(), "Пиво", "Напиток, но не овощь и все таки ов");
        Article earth           = new Article(UUID.randomUUID(), "Земля", "На планете Земля растут овощи и очень большие овощи");
        Article apple           = new Article(UUID.randomUUID(), "Яблоко", "На планете Земля растут овощи и очень большие овощи");
        articleMap.put(tomatoesTheBest.getId(), tomatoesTheBest);
        articleMap.put(potatoTheBest.getId(), potatoTheBest);
        articleMap.put(beerArticle.getId(), beerArticle);
        articleMap.put(earth.getId(), earth);
        articleMap.put(apple.getId(), apple);
    }

}