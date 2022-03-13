package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;

public class AssetImpl extends GsonWrapper implements Asset {

    public AssetImpl(JsonObject content) {
        super(content);
    }

    @Override
    public String getHref() {
        return this.getStringByKey("href");
    }

    @Override
    public Optional<String> getTitle() {
        return this.getOptStringByKey("title");
    }

    @Override
    public Optional<String> getDescription() {
        return this.getOptStringByKey("description");
    }

    @Override
    public Optional<String> getType() {
        return this.getOptStringByKey("type");
    }

    @Override
    public List<String> getRoles() {
        return this.getStringListByKey("roles");
    }

    @Override
    public String toString() {
        return String.format("AssetImpl{href: %s}", this.getHref());
    }
}
