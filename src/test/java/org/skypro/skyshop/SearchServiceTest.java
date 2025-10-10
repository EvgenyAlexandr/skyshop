package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    //Создание и управление moc объектом для SearchService - Заглушка
    @Mock
    private StorageService storageService;

    // Тестируемый класс
    @InjectMocks
    private SearchService searchService;

// Тест
//1. Поиск при отсутствии объектов
//2. Поиск при наличии подходящего объекта
//3. Поиск при наличии объектов, но отсутствии подходящих

    // Тест - Поиск при отсутствии объектов
    @Test
    public void productSearchInTheAbsenceOfAnObject () {
        String findText = "Свекла"; // Искомое слово

        // Проверяем - Метод поиска.
        Collection<SearchResult> result = searchService.search(findText);

        // Тест - Ожидаем пустую коллекцию.
        assertTrue(result.isEmpty());
    }

    // Тест - Поиск при наличии подходящего объекта
    @Test
    public void productSearchIfThereIs_a_SuitableObject() {
        String findText = "Свекла"; // Искомое слово
        //String findText = "Батон"; // Искомое слово

        Mockito.when(storageService.getAllProduct())                        // Вызываемый метод
                .thenReturn(List.of(
                        new SimpleProduct(UUID.randomUUID(), "Свекла",  30),
                        new SimpleProduct(UUID.randomUUID(), "Капуста", 54)
                )); // Результат который должен вернуть метод
        Collection<SearchResult> results = searchService.search(findText);

        // Коллекция не пуста. Есть совпадения.
        assertFalse(results.isEmpty());
    }

    // Тест - Поиск при наличии объектов, но отсутствии подходящих
    @Test
    public void searchFor_a_ProductThatIsNotAvailableIfThereIs_a_SuitableObject() {
        //String findText = "Свекла"; // Искомое слово
        String findText = "Батон"; // Искомое слово

        Mockito.when(storageService.getAllProduct())                        // Вызываемый метод
                .thenReturn(List.of(
                        new SimpleProduct(UUID.randomUUID(), "Свекла",  30),
                        new SimpleProduct(UUID.randomUUID(), "Капуста", 54)
                )); // Результат который должен вернуть метод
        Collection<SearchResult> results = searchService.search(findText);

        // Коллекция пуста. Нет совпадений.
        assertTrue(results.isEmpty());
    }

}