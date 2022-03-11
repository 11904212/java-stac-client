package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Link;
import com.google.gson.JsonObject;

import java.util.Optional;

public class LinkImpl2 extends GsonWrapper implements Link {


    public LinkImpl2(JsonObject content) {
        super(content);
    }

    @Override
    public String getHref() {
        return this.getStringByKey("href");
    }

    @Override
    public String getRel() {
        return this.getStringByKey("rel");
    }

    @Override
    public Optional<String> getType() {
        return this.getOptStringByKey("type");
    }

    @Override
    public Optional<String> getTitle() {
        return this.getOptStringByKey("title");
    }


    @Override
    public String toString() {
        return String.format("Link{%s: %s}", this.getRel(), this.getHref());
    }
}
