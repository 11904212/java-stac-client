package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Provider;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Optional;

public class ProviderImpl extends GsonWrapper implements Provider {

    public ProviderImpl(JsonObject content) {
        super(content);
    }

    @Override
    public String getName() {
        return this.getStringByKey("name");
    }

    @Override
    public Optional<String> getDescription() {
        return this.getOptStringByKey("description");
    }

    @Override
    public List<String> getRoles() {
        return this.getStringListByKey("roles");
    }

    @Override
    public Optional<String> getURL() {
        return this.getOptStringByKey("url");
    }

}
