package com.github11904212.java.stac.client;

import com.github11904212.java.stac.client.core.Collection;
import com.github11904212.java.stac.client.core.Catalog;
import com.github11904212.java.stac.client.core.Item;
import com.github11904212.java.stac.client.search.ItemCollection;
import com.github11904212.java.stac.client.search.dto.QueryParameter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * A client for communicating with a stac-api.
 * @see <a href="https://github.com/radiantearth/stac-spec">stac-spec</a>
 * @see <a href="https://github.com/radiantearth/stac-api-spec">stac-api-spec</a>
 * This module is partly inspired by pystac (implementation for python)
 * and uses jackson as json parser.
 */
public interface StacClient {

    /**
     * obtains the catalog from the landing page of a stac-api.
     * @return the catalog as {@link Catalog}.
     * @throws IOException if an error occurs.
     */
    Catalog getCatalog() throws IOException;

    /**
     * obtains a collection with the given id.
     * @param id the id of the {@link Collection}
     * @return an Optional of {@link Collection}, empty if not found.
     * @throws IOException if an error occurs.
     */
    Optional<Collection> getCollection(String id) throws IOException;

    /**
     * obtains an item with the given collectionId and itemId.
     * @param collectionId the id ot the collection.
     * @param itemId the id of the item.
     * @return an Optional of {@link Item}, empty if not found.
     * @throws IOException if an error occurs.
     */
    Optional<Item> getItem(String collectionId, String itemId) throws IOException;

    /**
     * search for items.
     * @param parameter the search criteria as {@link QueryParameter}.
     * @return a {@link ItemCollection} of found items.
     * @throws IOException if an error occurs.
     * @throws URISyntaxException if the generated search url is not valid.
     * @throws InterruptedException if the request got interrupted.
     */
    ItemCollection search(QueryParameter parameter) throws IOException, URISyntaxException, InterruptedException;
}
