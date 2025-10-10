package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    //Создание и управление moc объектом для SearchService
    @Mock
    private StorageService storageService;

    // Тестируемый класс
    @InjectMocks
    private SearchService searchService;
//*

// Тест
//1. Поиск при отсутствии объектов
//2. Поиск при наличии подходящего объекта
//3. Поиск при наличии объектов, но отсутствии подходящих

    // Тест - Поиск при отсутствии объектов
    @Test
    public void searchInCaseOfAbsenceOfObjects() {
        String findText = "Свекла"; // Искомое слово

        // Проверяем - Метод поиска.
        Collection<SearchResult> result = searchService.search(findText);

        // Тест - Ожидаем пустую коллекцию
        assertTrue(result.isEmpty());
    }

    // Тест - Поиск при наличии подходящего объекта
    @Test
    public void searchProductFound() {
        String findText = "Свекла"; // Искомое слово

        // Проверяем - Метод поиска.
        Collection<SearchResult> result = searchService.search(findText);

        // Тест - Ожидаем пустую коллекцию
        assertTrue(result.isEmpty());
    }


// */

    /*
//    @Mock
//    private StorageService storageServiceOne;
//    @InjectMocks
//    private SearchService searchServiceOne;
    @Test
    public void searchInCaseOfAbsenceOfObjects() {
        when(storageServiceOne.getSearchable()).thenReturn(Collections.emptyList());
        Collection<SearchResult> result = searchServiceOne.search("лобстер");
        assertTrue(result.isEmpty());
    }

    @Test
    public void search_ProductFound() {
        when(storageServiceOne.getSearchable()).thenReturn(List.of(
                new SimpleProduct(UUID.randomUUID(), "лобстер", 100)));
        List<SearchResult> results = searchServiceOne.search("лобстер");
        assertFalse(results.isEmpty());
        assertEquals(results.get(0).getName(), "лобстер");
    }

    @Test
    public void SearchInCaseThereAreObjectsButNoSuitableOne(){
        when(storageServiceOne.getSearchable()).thenReturn(List.of(
                new Article(UUID.randomUUID(), "рецепты приготовления блинчиков", "Домашние блинчики с начинкой")));
        List<SearchResult> result = searchServiceOne.search("блинчики");
        assertFalse(result.isEmpty());
    }




//     */






}