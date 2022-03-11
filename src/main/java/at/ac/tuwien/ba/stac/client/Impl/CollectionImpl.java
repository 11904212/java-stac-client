package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import at.ac.tuwien.ba.stac.client.Collection;
import at.ac.tuwien.ba.stac.client.Extent;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import at.ac.tuwien.ba.stac.client.Provider;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CollectionImpl extends GsonWrapper implements Collection {


    public CollectionImpl(JsonObject content) {
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
    public Optional<String> getTitle() {
        return this.getOptStringByKey("title");
    }

    @Override
    public String getDescription() {
        return this.getStringByKey("description");
    }

    @Override
    public List<String> getKeywords() {
        return this.getStringListByKey("keywords");
    }

    @Override
    public String getLicense() {
        return this.getStringByKey("license");
    }

    @Override
    public List<Provider> getProviders() {
        List<Provider> list = new ArrayList<>();
        String key = "providers";
        if (this.content.has(key)) {
            var array = this.content.get(key).getAsJsonArray();
            array.forEach(provider -> list.add(new ProviderImpl(provider.getAsJsonObject())));
        }
        return list;
    }

    @Override
    public Extent getExtend() {
        return new ExtentImpl(this.content.get("extent").getAsJsonObject());
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
    public List<Item> getItems() {
        String key = "items";
        List<Item> list = new ArrayList<>();
        if (this.content.has(key)) {
            JsonArray array = this.content.get(key).getAsJsonArray();
            array.forEach(link -> list.add(new ItemImpl(link.getAsJsonObject())));
        }
        return list;
    }

    @Override
    public String toString() {
        return String.format("CollectionImpl{%s}",this.getId());
    }
}
