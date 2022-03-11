package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Asset;
import at.ac.tuwien.ba.stac.client.Link;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GsonWrapper {
    protected final JsonObject content;

    public GsonWrapper(JsonObject content) {
        this.content = content;
    }

    protected String getStringByKey(String key) {
        return this.content.get(key).getAsString();
    }

    protected Optional<String> getOptStringByKey(String key) {
        return this.content.has(key) ?
                Optional.of(this.content.getAsJsonPrimitive(key).getAsString()) : Optional.empty();
    }

    protected List<String> getStringListByKey(String key) {
        List<String> res = new ArrayList<>();
        if(this.content.has(key)) {
            JsonArray array = this.content.getAsJsonArray(key);
            array.forEach(extend -> res.add(extend.getAsString()));
        }
        return res;
    }

    protected JsonObject getJsonObjectByKey(String key) {
        return this.content.get(key).getAsJsonObject();
    }

    protected List<Link> getLinkList() {
        String key = "links";
        List<Link> list = new ArrayList<>();
        if (this.content.has(key)) {
            JsonArray array = this.content.get(key).getAsJsonArray();
            array.forEach(link -> list.add(new LinkImpl2(link.getAsJsonObject())));
        }
        return list;
    }

    protected Map<String, Asset> getAssetsMap() {
        String key = "assets";
        Map<String, Asset> map = new HashMap<>();
        if (this.content.has(key)) {
            JsonObject assets = this.content.get(key).getAsJsonObject();
            assets.entrySet().forEach(entry ->
                    map.put(entry.getKey(), new AssetImpl(entry.getValue().getAsJsonObject()))
            );
        }
        return map;
    }
}
