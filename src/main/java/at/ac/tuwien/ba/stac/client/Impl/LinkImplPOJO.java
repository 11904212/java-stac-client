package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Link;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Optional;

public class LinkImplPOJO implements Link {

    private final String href;
    private final String rel;
    private String type;
    private String title;

    @JsonCreator
    public LinkImplPOJO(
            @JsonProperty("href") String href,
            @JsonProperty("rel") String rel
    ) {
        this.href = href;
        this.rel = rel;
    }

    @JsonSetter("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonSetter("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getHref() {
        return this.href;
    }

    @Override
    public String getRel() {
        return this.rel;
    }

    @Override
    public Optional<String> getType() {
        return this.type != null ? Optional.of(this.type) : Optional.empty();
    }

    @Override
    public Optional<String> getTitle() {
        return this.title != null ? Optional.of(this.title) : Optional.empty();
    }

    @Override
    public String toString() {
        return String.format("Link{%s: %s}", this.getRel(), this.getHref());
    }
}
