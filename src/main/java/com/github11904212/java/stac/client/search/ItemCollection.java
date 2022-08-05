package com.github11904212.java.stac.client.search;

import com.github11904212.java.stac.client.core.Item;
import com.github11904212.java.stac.client.core.Link;

import java.util.List;

/**
 * A ItemCollection is a container holding items and links which usually represent the result of a search.
 * @see <a href="https://github.com/radiantearth/stac-api-spec/tree/main/fragments/itemcollection">itemcollection-spec</a>
 */
public interface ItemCollection {

    /**
     * the type of the item-collection (usually FeatureCollection).
     * @return the type.
     */
    String getType();

    /**
     * a possibly-empty list of items.
     * @return the list of {@link Item}s.
     */
    List<Item> getItems();

    /**
     * a list of links related to this item-collection.
     * @return the list of {@link Link}s.
     */
    List<Link> getLinks();

}
