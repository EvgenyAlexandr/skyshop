package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private static String TERM_ARTICLE = "ARTICLE";
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + '\n' + text;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContent() {
        return TERM_ARTICLE;
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        Searchable art = (Searchable) other;
        return Objects.equals(title, art.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public UUID getId() {
        return id;
    }
}