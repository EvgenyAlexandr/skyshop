package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {
    ///  Поисковый сервис
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    // Получение данных для поиска
    private Collection<Searchable> getSearchable() {
        return Stream.concat(storageService.getAllArticle().stream(),   // Берет все статьи
                        storageService.getAllProduct().stream())        // Берет все товары
                .collect(Collectors.toList());                          // Объединяет их в один поток (Stream) и преобразует в список
    }

    // Основной метод поиска
    public Collection<SearchResult> search(String findText) {
        return getSearchable().stream()
                .filter(Objects::nonNull)                                             // Шаг 1: убирает null-элементы
                .filter(element -> element.searchTerm().contains(findText)) // Шаг 2: ищет совпадения
                .map(element -> new SearchResult(                           // Шаг 3: преобразует в результат
                        element.getId(),
                        element.getName(),
                        element.getContent()))
                .collect(Collectors.toList());                                        // Шаг 4: собирает в список
    }
}
