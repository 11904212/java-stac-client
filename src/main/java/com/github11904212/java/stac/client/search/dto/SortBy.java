package com.github11904212.java.stac.client.search.dto;

public class SortBy {
    private final String field;
    private final SortDirection direction;

    public SortBy(String field, SortDirection direction) {
        this.field = field;
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public SortDirection getDirection() {
        return direction;
    }

}
