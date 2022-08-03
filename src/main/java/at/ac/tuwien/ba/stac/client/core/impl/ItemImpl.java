package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.Asset;
import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.core.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mil.nga.sf.geojson.Feature;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@JsonTypeName("Feature")
public class ItemImpl extends Feature implements Item {

    private final String stacVersion;
    private final List<String> stacExtensions;
    private final List<Link> links;
    private final Map<String, Asset> assets;
    private final String collection;

    @JsonCreator
    public ItemImpl(
            @JsonProperty(value = "stac_version", required = true)
                    String stacVersion,
            @JsonProperty(value = "stac_extensions")
                    List<String> stacExtensions,
            @JsonProperty(value = "links", required = true) @JsonDeserialize(contentAs= LinkImpl.class)
                    List<Link> links,
            @JsonProperty(value = "assets", required = true) @JsonDeserialize(contentAs = AssetImpl.class)
                    Map<String, Asset> assets,
            @JsonProperty(value = "collection")
                    String collection
    ) {

        this.stacVersion = stacVersion;
        this.links = links;
        this.assets = assets;

        this.stacExtensions = Objects.requireNonNullElse(stacExtensions, Collections.emptyList());
        this.collection = collection;
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
    public List<Link> getLinks() {
        return this.links;
    }

    @Override
    public Map<String, Asset> getAssets() {
        return this.assets;
    }

    @Override
    public Optional<String> getCollection() {
        return Optional.ofNullable(this.collection);
    }

    @Override
    public Optional<String> getDateTime() {
        return Optional.ofNullable((String) this.getProperties().get("datetime"));
    }

    @Override
    public Optional<Asset> getAsset(String key) {
        return Optional.ofNullable(this.assets.get(key));
    }

    @Override
    public String toString() {
        return String.format("Item{%s}",this.getId());
    }
}
