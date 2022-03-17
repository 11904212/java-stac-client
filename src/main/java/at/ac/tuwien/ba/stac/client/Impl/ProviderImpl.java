package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Provider;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProviderImpl implements Provider {

    private final String name;
    private final String description;
    private final List<String> roles;
    private final String url;

    @JsonCreator
    public ProviderImpl(
            @JsonProperty("name") String name,
            @JsonProperty("description")String description,
            @JsonProperty("roles")List<String> roles,
            @JsonProperty("url")String url
    ) {
        this.name = name;
        this.description = description;
        this.roles = roles != null ? roles : Collections.emptyList();
        this.url = url;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public Optional<String> getURL() {
        return Optional.ofNullable(url);
    }
}
