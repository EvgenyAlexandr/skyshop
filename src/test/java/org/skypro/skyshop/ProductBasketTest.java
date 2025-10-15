package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductBasketTest {

    @Mock
    private Product mockProduct;

    @InjectMocks
    private ProductBasket productBasket;

    //@BeforeEach
        // Метода, который будет выполняться перед каждым тестовым методом в тестовом классе
    void setUp() {
        when(mockProduct.getName()).thenReturn("Test Product");
        when(mockProduct.getPrice()).thenReturn(100);
        when(mockProduct.isSpecial()).thenReturn(false);
    }

    // Тест на добавление несуществующего товара
    @Test
    void testAddNonExistingProduct() {
        // Создаем продукт с пустым именем, что должно вызвать исключение
        assertThrows(IllegalArgumentException.class, () -> {
            Product invalidProduct = new Product(UUID.randomUUID(), null) {
                @Override
                public boolean isSpecial() {
                return false;
                }

                @Override
                public int getPrice() {
                    return 0;
                }
            };

            productBasket.addProduct(invalidProduct);
        });
    }

    // Тест на добавление существующего товара
    @Test
    void testAddExistingProduct() {

//        when(mockProduct.getName()).thenReturn("Test Product");
//        when(mockProduct.getPrice()).thenReturn(100);
        setUp();

        // Добавляем продукт
        productBasket.addProduct(mockProduct);

        // Не знаю что он такого делает, но без этого не работает
        productBasket.printBacket();


        // Проверяем, что продукт добавлен
        assertTrue(productBasket.containsProduct("Test Product"));

        // Проверяем вызов метода addProduct
        verify(mockProduct, times(1)).getName();
        verify(mockProduct, times(1)).getPrice();
    }

    // Тест на получение пустой корзины
    @Test
    void testGetEmptyBasket() {
        // Проверяем, что корзина пуста
        assertEquals(0, productBasket.getTotalCost());
    }

    // Тест на получение корзины с товарами
    @Test
    void testGetFilledBasket() {

        setUp();

        // Добавляем несколько продуктов
        productBasket.addProduct(mockProduct);
        productBasket.addProduct(mockProduct);

        // Не знаю что он такого делает, но без этого не работает
        productBasket.printBacket();

        // Проверяем содержимое корзины
        assertEquals(200, productBasket.getTotalCost());

    }

    // Дополнительный тест на удаление товара
    @Test
    void testRemoveProduct() {

        setUp();

        // Добавляем и удаляем продукт
        productBasket.addProduct(mockProduct);

        // Не знаю что делает, но без этого не работает
        productBasket.printBacket();

        List<Product> removed = productBasket.removeProduct("Test Product");

        // Проверяем результат
        assertTrue(removed.contains(mockProduct));
        assertFalse(productBasket.containsProduct("Test Product"));
    }

    // Тест на очистку корзины
    @Test
    void testClearBasket() {
        // Заполняем и очищаем корзину
        productBasket.addProduct(mockProduct);
        productBasket.clearBasket();

        // Проверяем результат
        assertEquals(0, productBasket.getTotalCost());
    }
}




