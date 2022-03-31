package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import mil.nga.sf.geojson.Feature;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@JsonTypeName("Feature")
public class ItemImpl extends Feature implements Item {

    private String stacVersion;
    private List<String> stacExtensions = Collections.emptyList();
    private List<Link> links = Collections.emptyList();
    private Map<String, Asset> assets = Collections.emptyMap();
    private String collection;

    @JsonCreator
    public ItemImpl() {
    }

    @JsonSetter("stac_version")
    public void setStacVersion(String stacVersion) {
        this.stacVersion = stacVersion;
    }

    @JsonSetter("stac_extensions")
    public void setStacExtensions(List<String> stacExtensions) {
        this.stacExtensions = stacExtensions;
    }

    @JsonSetter("links")
    @JsonDeserialize(contentAs = LinkImpl.class)
    public void setLinks( List<Link> links) {
        this.links = links;
    }

    @JsonSetter("assets")
    @JsonDeserialize(contentAs = AssetImpl.class)
    public void setAssets( Map<String, Asset> assets) {
        this.assets = assets;
    }

    @JsonSetter("collection")
    public void setCollection(String collection) {
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
