package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();

    String getContent();

    String getName();

    default String getStringRepresentation() {
        return getName() + " - " + getContent();
    }

    UUID getId();
}