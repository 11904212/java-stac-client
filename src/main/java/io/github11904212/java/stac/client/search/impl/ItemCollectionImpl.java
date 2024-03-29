package io.github11904212.java.stac.client.search.impl;

import io.github11904212.java.stac.client.core.Item;
import io.github11904212.java.stac.client.core.Link;
import io.github11904212.java.stac.client.core.impl.ItemImpl;
import io.github11904212.java.stac.client.core.impl.LinkImpl;
import io.github11904212.java.stac.client.search.ItemCollection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class ItemCollectionImpl implements ItemCollection {

    private final String type;
    private final List<Item> items;
    private final List<Link> links;

    @JsonCreator
    public ItemCollectionImpl(
            @JsonProperty(value = "type", required = true)
                    String type,

            @JsonProperty(value = "features", required = true) @JsonDeserialize(contentAs = ItemImpl.class)
                    List<Item> items,

            @JsonProperty("links") @JsonDeserialize(contentAs = LinkImpl.class)
                    List<Link> links
    ) {
        this.type = type;
        this.items = items;
        this.links = links;
    }


    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public List<Item> getItems() {
        return this.items;
    }

    @Override
    public List<Link> getLinks() {
        return this.links;
    }
}
