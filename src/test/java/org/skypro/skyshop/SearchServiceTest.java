package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    //Создание и управление moc объектом для SearchService - Заглушка
    @Mock
    private StorageService storageService;

    // Тестируемый класс
    @InjectMocks
    private SearchService searchService;

    // Тесты
    // 1. Поиск при отсутствии объектов StorageService
    // 2. Поиск при наличии объектов, но отсутствии подходящих
    // 3. Поиск при наличии подходящего объекта

    // Тест 1 - Поиск при отсутствии объектов StorageService
    @Test
    public void search_whenNoProducts_shouldReturnEmpty() {
        String findText = "Свекла"; // Искомое слово

        // Проверяем - Метод поиска.
        Collection<SearchResult> result = searchService.search(findText);

        // Тест - Ожидаем пустую коллекцию.
        assertTrue(result.isEmpty());
    }

    // Тест 2 - Поиск при наличии объектов, но отсутствии подходящих
    @Test
    public void search_whenNoMatchingProducts_shouldReturnEmpty() {
        String findText = "Батон"; // Искомое слово

        Mockito.when(storageService.getAllProduct())     // Вызываемый метод
                .thenReturn(List.of(                     // Результат который должен вернуть метод
                        new SimpleProduct(UUID.randomUUID(), "Свекла",  30),
                        new SimpleProduct(UUID.randomUUID(), "Капуста", 54)
                ));
        Collection<SearchResult> results = searchService.search(findText);

        // Коллекция пуста. Нет совпадений.
        assertTrue(results.isEmpty());
    }

    // Тест 3 - Поиск при наличии подходящего объекта
    @Test
    public void search_whenMatchingProductsExist_shouldReturnResults() {
        String findText = "Свекла"; // Искомое слово

        Mockito.when(storageService.getAllProduct())     // Вызываемый метод
                .thenReturn(List.of(                     // Результат который должен вернуть метод
                        new SimpleProduct(UUID.randomUUID(), "Свекла",  30),
                        new SimpleProduct(UUID.randomUUID(), "Капуста", 54)
                ));
        Collection<SearchResult> results = searchService.search(findText);

        // Коллекция не пуста. Есть совпадения.
        assertFalse(results.isEmpty());
        // В полученной коллекции есть искомый текст
        assertTrue(results.stream()     // Преобразуем коллекцию в поток для обработки
                .anyMatch(              // Метод возвращает true, если хотя бы один элемент в потоке удовлетворяет условию
                        searchResult -> searchResult.getName().contains(findText))
                );

        /*
        // В коллекции только один элемент
        assertEquals(1, results.size());
        // В полученной коллекции есть искомый текст
        assertEquals(findText, results.iterator().next().getName());
        */


    }
}