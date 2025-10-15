package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {

    private static String   TERM_ARTICLE = "ARTICLE";
    private final UUID      id;        // UUID — уникального идентификатор
    private final String    title;     // Название Статьи
    private final String    text;      // Текст статьи

    // Конструктор
    public Article(UUID id, String title, String text) {
        this.id     = id;
        this.title  = title;
        this.text   = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    // Сравнение объектов этого класса
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        Searchable art = (Searchable) other;
        return Objects.equals(title, art.getName());
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    // Строковое представление объекта
    @Override
    public String toString() {
        return title + '\n' + text;
    }
}