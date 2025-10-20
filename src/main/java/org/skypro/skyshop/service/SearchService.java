package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    private Collection<Searchable> getSearchable() {
        return Stream.concat(storageService.getAllArticle().stream(), storageService.getAllProduct().stream())
                .collect(Collectors.toList());
    }

    public Collection<SearchResult> search(String findText) {
        List<SearchResult> results = storageService.getAllProduct().stream()
                .filter(s -> s.searchTerm().contains(findText))
                .map(SearchResult::fromSearchable)
                .toList();
        return results;


    }
}
