package io.github11904212.java.stac.client.search.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortDirection {
    ASC,
    DESC;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
