package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CatalogImpl extends NonStacProperties implements Catalog {

    private final String type;
    private final String stacVersion;
    private final List<String> stacExtensions;
    private final String id;
    private final String title;
    private final String description;
    private final List<Link> links;


    @JsonCreator
    public CatalogImpl(
            @JsonProperty(value = "type", required = true)
                    String type,

            @JsonProperty(value = "stac_version", required = true)
                    String stacVersion,

            @JsonProperty(value = "stac_extensions")
                    List<String> stacExtensions,

            @JsonProperty(value = "id", required = true)
                    String id,

            @JsonProperty(value = "title")
                    String title,

            @JsonProperty(value = "description", required = true)
                    String description,

            @JsonProperty(value = "links", required = true) @JsonDeserialize(contentAs= LinkImpl.class)
                    List<Link> links
    ) {
        this.type = type;
        this.stacVersion = stacVersion;
        this.stacExtensions = Objects.requireNonNullElse(stacExtensions, Collections.emptyList());
        this.id = id;
        this.title = title;
        this.description = description;
        this.links = links;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getStacVersion() {
        return this.stacVersion;
    }

    @Override
    public List<String> getStacExtensions() {
        return this.stacExtensions;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public List<Link> getLinks() {
        return this.links;
    }

    @Override
    public String toString() {
        return String.format("Collection{%s}",this.getId());
    }

}
