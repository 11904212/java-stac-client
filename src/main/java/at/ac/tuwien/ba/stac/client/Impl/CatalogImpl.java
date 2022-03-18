package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Catalog;
import at.ac.tuwien.ba.stac.client.Collection;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogImpl extends NonStacProperties implements Catalog {

    private final String type;
    private final String stacVersion;
    private List<String> stacExtensions = new ArrayList<>();
    private final String id;
    private String title;
    private final String description;
    private final List<Link> links;


    @JsonCreator
    public CatalogImpl(
            @JsonProperty("type") String type,
            @JsonProperty("stac_version") String stacVersion,
            @JsonProperty("id") String id,
            @JsonProperty("description") String description,
            @JsonProperty("links") @JsonDeserialize(contentAs= LinkImpl.class) List<Link> links
    ) {
        this.type = type;
        this.stacVersion = stacVersion;
        this.id = id;
        this.description = description;
        this.links = links;
    }

    @JsonSetter("stac_extensions")
    public void setStacExtensions(List<String> stacExtensions) {
        this.stacExtensions = stacExtensions;
    }

    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
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
    public Collection getCollection(String collectionId) {
        return null;
    }

    @Override
    public List<Collection> getCollections() {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Collection{%s}",this.getId());
    }

}
