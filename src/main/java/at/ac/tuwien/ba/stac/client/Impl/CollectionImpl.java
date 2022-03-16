package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import at.ac.tuwien.ba.stac.client.Collection;
import at.ac.tuwien.ba.stac.client.Extent;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import at.ac.tuwien.ba.stac.client.Provider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CollectionImpl implements Collection {

    private final String type;
    private final String stacVersion;
    private List<String> stacExtensions = new ArrayList<>();
    private final String id;
    private String title;
    private final String description;
    private List<String> keywords = new ArrayList<>();
    private final String license;
    private List<Provider> providers = new ArrayList<>();
    private final Extent extent;
    private final List<Link> links;
    private Map<String, Asset> assets = new HashMap<>();

    @JsonCreator
    public CollectionImpl(
            @JsonProperty("type") String type,
            @JsonProperty("stac_version") String stacVersion,
            @JsonProperty("id") String id,
            @JsonProperty("description") String description,
            @JsonProperty("license") String license,
            @JsonProperty("extent") @JsonDeserialize(as = ExtentImpl.class) Extent extent,
            @JsonProperty("links") @JsonDeserialize(contentAs= LinkImpl.class) List<Link> links
    ) {
        this.type = type;
        this.stacVersion = stacVersion;
        this.id = id;
        this.description = description;
        this.license = license;
        this.extent = extent;
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

    @JsonSetter("keywords")
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @JsonSetter("providers")
    @JsonDeserialize(contentAs= ProviderImpl.class)
    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    @JsonSetter("assets")
    @JsonDeserialize(contentAs= AssetImpl.class)
    public void setAssets(Map<String, Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getStacVersion() {
        return stacVersion;
    }

    @Override
    public List<String> getStacExtensions() {
        return stacExtensions;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String getLicense() {
        return license;
    }

    @Override
    public List<Provider> getProviders() {
        return providers;
    }

    @Override
    public Extent getExtend() {
        return extent;
    }

    @Override
    public List<Link> getLinks() {
        return links;
    }

    @Override
    public Map<String, Asset> getAssets() {
        return assets;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("CollectionImpl{%s}",this.getId());
    }
}
