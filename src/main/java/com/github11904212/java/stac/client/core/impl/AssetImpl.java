package com.github11904212.java.stac.client.core.impl;

import com.github11904212.java.stac.client.core.Asset;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AssetImpl extends NonStacProperties implements Asset {

    private final String href;
    private final String title;
    private final String description;
    private final String type;
    private final List<String> roles;

    @JsonCreator
    public AssetImpl(
            @JsonProperty(value = "href", required = true) String href,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("type") String type,
            @JsonProperty("roles") List<String> roles
    ) {
        this.href = href;
        this.title = title;
        this.description = description;
        this.type = type;
        this.roles = roles != null ? roles : Collections.emptyList();
    }

    @Override
    public String getHref() {
        return href;
    }

    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return String.format("Asset{href: %s}", this.getHref());
    }
}
