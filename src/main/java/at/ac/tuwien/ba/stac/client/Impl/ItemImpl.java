package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import at.ac.tuwien.ba.stac.client.Properties;
import com.google.gson.JsonObject;
import org.geotools.geojson.GeoJSON;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemImpl extends GsonWrapper implements Item {
    public ItemImpl(JsonObject content) {
        super(content);
    }

    @Override
    public String getType() {
        return this.getStringByKey("type");
    }

    @Override
    public String getStacVersion() {
        return this.getStringByKey("stac_version");
    }

    @Override
    public List<String> getStacExtensions() {
        return this.getStringListByKey("stac_extensions");
    }

    @Override
    public String getId() {
        return this.getStringByKey("id");
    }

    @Override
    public Optional<GeoJSON> getGeometry() {
        String key = "geometry";
        if (this.content.has(key)) {
            // TODO parse json
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Double> getBbox() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public List<Link> getLinks() {
        return this.getLinkList();
    }

    @Override
    public Map<String, Asset> getAssets() {
        return this.getAssetsMap();
    }

    @Override
    public Optional<String> getCollection() {
        return this.getOptStringByKey("collection");
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public Asset getAsset(String key) {
        return null;
    }
}
