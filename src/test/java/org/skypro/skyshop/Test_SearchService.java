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
        Assertions.assertEquals(result,null);
    }

}