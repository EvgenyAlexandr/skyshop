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

    private Collection<Searchable> getSearchable() {
        return Stream.concat(storageService.getAllArticle().stream(), storageService.getAllProduct().stream())
                .collect(Collectors.toList());
    }

    public Collection<SearchResult> search(String findText) {
        return getSearchable().stream()
                .filter(Objects::nonNull)
                .filter(element -> element.searchTerm().contains(findText))
                .map(element -> (new SearchResult(element.getId(), element.getName(), element.getContent())))
                .collect(Collectors.toList());
    }
}
