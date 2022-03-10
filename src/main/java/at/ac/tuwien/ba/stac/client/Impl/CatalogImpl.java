package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Catalog;
import at.ac.tuwien.ba.stac.client.Collection;
import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.Link;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogImpl implements Catalog {

    private final JSONObject content;

    public CatalogImpl(JSONObject content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return (String) content.get("type");
    }

    @Override
    public String getStacVersion() {
        return (String) content.get("stac_version");
    }

    @Override
    public List<String> getStacExtensions() {
        String key = "stac_extensions";
        List<String> res = new ArrayList<>();
        if(content.containsKey(key)) {
            JSONArray extensionsArr = (JSONArray) content.get(key);
            extensionsArr.forEach(extension -> res.add((String) extension));
        }
        return res;
    }

    @Override
    public String getId() {
        return (String) content.get("id");
    }

    @Override
    public Optional<String> getTitle() {
        String key = "title";
        if (content.containsKey(key)) {
            return Optional.of((String) content.get(key));
        }
        return Optional.empty();
    }

    @Override
    public String getDescription() {
        return (String) content.get("description");
    }

    @Override
    public List<Link> getLinks() {
        String key = "links";
        List<Link> res = new ArrayList<>();
        JSONArray linksArr = (JSONArray) content.get(key);
        linksArr.forEach(link -> res.add(new LinkImpl((JSONObject) link)));
        return res;
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
        return String.format("Catalog{%s}",this.getId());
    }
}
