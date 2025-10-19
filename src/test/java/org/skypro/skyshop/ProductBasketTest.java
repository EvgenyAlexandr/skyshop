package org.skypro.skyshop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тест корзины")
class ProductBasketTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;


    @Test
    @DisplayName("Добавление не существующего товара")
    void givenNon_ExistentProduct_whenAddToBasket_ThenThrowException() {

        // Подготавливаем несуществующий ID
        UUID nonExistentId = UUID.randomUUID();

        // Настраиваем мок: продукт не найден
        Mockito.when(storageService.getProductById(nonExistentId))
                .thenReturn(Optional.empty());

        // Проверяем, что метод выбрасывает ожидаемое исключение
        assertThatThrownBy(() -> basketService.addToBasket(nonExistentId))
                .isInstanceOf(NoSuchProductException.class);

        // Дополнительно проверяем, что storageService был вызван
        Mockito.verify(storageService).getProductById(nonExistentId);
    }


    @Test
    @DisplayName("Добавление существующего товара в корзину")
    void shouldAddExistingProductToBasketSuccessfully() {

        // Подготовка данных и моков
        UUID id = UUID.randomUUID();
        SimpleProduct expectedProduct = new SimpleProduct(id,"Яблоко", 12);

        // Мокируем успешное получение продукта из хранилища
        Mockito.when(storageService.getProductById(id))
                .thenReturn(Optional.of(expectedProduct));

        // Проверка отсутствия исключений - При добавлении товара не выбрасывается исключение.
        assertDoesNotThrow(() -> basketService.addToBasket(id));

        // Проверка вызова метода мока
        verify(productBasket).addProduct(id);
    }

    @Test
    @DisplayName("Возвращаем пустую корзину")
    public void givenEmptyProductBasket_whenGetUserBasket_ThenEmptyUserBasket() {
        UserBasket result = basketService.getUserBasket();
        assertTrue(result.getItemsList().isEmpty());
    }

    @Test
    @DisplayName("Возвращаем подходящую корзину")
    public void givenFillProductBasket_whenGetUserBasket_ThenGetUserBasket() {

        // Подготовка данных и моков
        UUID id = UUID.randomUUID();
        SimpleProduct expectedProduct = new SimpleProduct(id,"Яблоко", 12);
        int quantity = 1;           // Явно задаём количество

        // Формируем карту продуктов в корзине
        Map<UUID, Integer> basketProducts = Map.of(id, quantity);

        // Мокируем успешное получение продукта из хранилища
        Mockito.when(storageService.getProductById(id))
                .thenReturn(Optional.of(expectedProduct));

        // Моктруем метод возвращающий содержимое корзины
        Mockito.when(productBasket.getProductBasket()).thenReturn(basketProducts);


        // Вызываем метод отображения корзины пользователю
        UserBasket result = basketService.getUserBasket();

        // Убеждаемся, что корзина не пуста (есть хотя бы один товар)
        assertFalse(result.getItemsList().isEmpty());
        // Проверяем, что первый товар в корзине имеет ожидаемый id
        assertEquals(result.getItemsList().get(0).getProduct().getId(), id);
    }


}




