package at.ac.tuwien.ba.stac.client.core.impl;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class NonStacProperties {

    private final Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void addNonStacProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    public Optional<Object> getNonStacProperty(String key) {
        return this.properties.containsKey(key) ? Optional.of(this.properties.get(key)) : Optional.empty();
    }

    public Set<String> getNonStacPropertiesKeys() {
        return this.properties.keySet();
    }
}
