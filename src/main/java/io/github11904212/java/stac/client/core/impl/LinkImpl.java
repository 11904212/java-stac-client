package io.github11904212.java.stac.client.core.impl;

import io.github11904212.java.stac.client.core.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class LinkImpl implements Link {

    private final String href;
    private final String rel;
    private final String type;
    private final String title;

    @JsonCreator
    public LinkImpl(
            @JsonProperty(value = "href", required = true) String href,
            @JsonProperty(value = "rel", required = true) String rel,
            @JsonProperty("type") String type,
            @JsonProperty("title") String title
    ) {
        this.href = href;
        this.rel = rel;
        this.type = type;
        this.title = title;
    }

    @Override
    public String getHref() {
        return this.href;
    }

    @Override
    public String getRel() {
        return this.rel;
    }

    @Override
    public Optional<String> getType() {
        return Optional.ofNullable(this.type);
    }

    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }

    @Override
    public String toString() {
        return String.format("Link{%s: %s}", this.getRel(), this.getHref());
    }
}
