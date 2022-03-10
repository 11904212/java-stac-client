package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Link;
import org.json.simple.JSONObject;

import java.util.Optional;

public class LinkImpl implements Link {

    private final JSONObject content;

    public LinkImpl(JSONObject content) {
        this.content = content;
    }

    @Override
    public String getHref() {
        return (String) content.get("href");
    }

    @Override
    public String getRel() {
        return (String) content.get("rel");
    }

    @Override
    public Optional<String> getType() {
        return getByKey("type");
    }

    @Override
    public Optional<String> getTitle() {
        return getByKey("title");
    }

    private Optional<String> getByKey(String key) {
        return content.containsKey(key) ?
                Optional.of((String) content.get(key)) : Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("Link{%s: %s}", this.getRel(), this.getHref());
    }
}
