package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Catalog;
import at.ac.tuwien.ba.stac.client.Collection;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CatalogImplPOJO implements Catalog {

    private final String type;
    private final String stacVersion;
    private List<String> stacExtensions = new ArrayList<>();
    private final String id;
    private String title;
    private final String description;
    private final List<Link> links;

    private final Map<String, Object> unknownProperties = new HashMap<>();


    @JsonCreator
    public CatalogImplPOJO(
            @JsonProperty("type") String type,
            @JsonProperty("stac_version") String stacVersion,
            @JsonProperty("id") String id,
            @JsonProperty("description") String description,
            @JsonProperty("links") @JsonDeserialize(contentAs=LinkImplPOJO.class) List<Link> links
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

    @JsonAnySetter
    public void addUnknownProperty(String key, Object value) {
        this.unknownProperties.put(key, value);
    }

    public Optional<Object> getUnknownProperty(String key) {
        return this.unknownProperties.containsKey(key) ? Optional.of(this.unknownProperties.get(key)) : Optional.empty();
    }

    public Set<String> getUnknownPropertiesKey() {
        return this.unknownProperties.keySet();
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
        return this.title != null ? Optional.of(this.title) : Optional.empty();
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
        return String.format("CollectionImpl{%s}",this.getId());
    }

}
