package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Item;
import at.ac.tuwien.ba.stac.client.ItemCollection;
import at.ac.tuwien.ba.stac.client.Link;
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
            @JsonProperty("type") String type,
            @JsonProperty("features") @JsonDeserialize(contentAs = ItemImpl.class) List<Item> items,
            @JsonProperty("links") @JsonDeserialize(contentAs = LinkImpl.class) List<Link> links
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
