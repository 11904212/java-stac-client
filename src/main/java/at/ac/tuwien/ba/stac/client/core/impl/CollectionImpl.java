package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.Asset;
import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.Extent;
import at.ac.tuwien.ba.stac.client.core.Link;
import at.ac.tuwien.ba.stac.client.core.Provider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CollectionImpl implements Collection {

    private final String type;
    private final String stacVersion;
    private final List<String> stacExtensions;
    private final String id;
    private final String title;
    private final String description;
    private final List<String> keywords;
    private final String license;
    private final List<Provider> providers;
    private final Extent extent;
    private final List<Link> links;
    private final Map<String, Asset> assets;

    @JsonCreator
    public CollectionImpl(
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

            @JsonProperty(value = "keywords")
                    List<String> keywords,

            @JsonProperty(value = "license", required = true)
                    String license,

            @JsonProperty(value = "providers") @JsonDeserialize(contentAs= ProviderImpl.class)
            List<Provider> providers,

            @JsonProperty(value = "extent", required = true) @JsonDeserialize(as = ExtentImpl.class)
                    Extent extent,

            @JsonProperty(value = "links", required = true) @JsonDeserialize(contentAs= LinkImpl.class)
                    List<Link> links,

            @JsonProperty(value = "assets") @JsonDeserialize(contentAs= AssetImpl.class)
                    Map<String, Asset> assets
    ) {
        this.type = type;
        this.stacVersion = stacVersion;
        this.stacExtensions = Objects.requireNonNullElse(stacExtensions, Collections.emptyList());
        this.id = id;
        this.title = title;
        this.description = description;
        this.keywords = Objects.requireNonNullElse(keywords, Collections.emptyList());
        this.license = license;
        this.providers = Objects.requireNonNullElse(providers, Collections.emptyList());
        this.extent = extent;
        this.links = links;
        this.assets = Objects.requireNonNullElse(assets, Collections.emptyMap());
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
    public String toString() {
        return String.format("CollectionImpl{%s}",this.getId());
    }
}
