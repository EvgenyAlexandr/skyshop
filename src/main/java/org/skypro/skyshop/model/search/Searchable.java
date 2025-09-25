package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    // метод, возвращающий, искомый текст.
    String searchTerm();

    // Контент
    String getContent();

    // Метод, получение имени объекта
    String getName();

    // Метод получения имени Searchable объекта.
    default String getStringRepresentation() {
        return getName() + " - " + getContent();
    }

    UUID getId();
}