package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class Test_SearchService {
    //Создание и управление moc объектом для SearchService
    @Mock
    private StorageService storageService;

    // Тестируемый класс
    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenFind_WhenSearch_ThenAll() {
        String findText = "овощ";
        Collection<SearchResult> result = searchService.search(findText);
        System.out.println(result);
        // Проверка
        //Assertions.assertEquals(result, null);
        //Assertions.assertEquals(null, null);
        assertNotNull(result, "Тест не пройден. Значение равно null");
        //assertTrue(result.contains("овощ"));
    }

//    @Test
//    public void givenFind_WhenSearch_ThenAll() {
//        String findText = "овощ";
//
////        // Настройка мока
////        when(storageService.getData())
////                .thenReturn(List.of("овощ1", "овощ2", "фрукт"));
//
//        Collection<SearchResult> result = searchService.search(findText);
//
//        // Проверки
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertTrue(result.contains("овощ1"));
//        assertTrue(result.contains("овощ2"));
//    }
}