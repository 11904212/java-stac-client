package io.github11904212.java.stac.client.impl;

import io.github11904212.java.stac.client.StacClient;
import io.github11904212.java.stac.client.core.Catalog;
import io.github11904212.java.stac.client.core.Collection;
import io.github11904212.java.stac.client.core.Item;
import io.github11904212.java.stac.client.core.impl.CatalogImpl;
import io.github11904212.java.stac.client.core.impl.CollectionImpl;
import io.github11904212.java.stac.client.core.impl.ItemImpl;
import io.github11904212.java.stac.client.search.dto.QueryParameter;
import io.github11904212.java.stac.client.search.impl.ItemCollectionImpl;
import io.github11904212.java.stac.client.search.ItemCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class StacClientImpl implements StacClient {

    private static final String JSON_MIME_TYPE = "application/json";

    private final ObjectMapper mapper;
    private final URL landingPage;
    private final HttpClient httpClient;
    private final HttpRequest.Builder searchReqBuilder;

    /**
     * creates a {@link StacClient}.
     * @param landingPage a {@link URL} pointing to the landing page of a STAC API.
     *                    the given URL must end with a trailing slash (../v1/).
     */
    public StacClientImpl(URL landingPage) {
        this.landingPage = landingPage;
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.httpClient = HttpClient.newHttpClient();
        this.searchReqBuilder = HttpRequest.newBuilder()
                .uri(URI.create(this.landingPage + "search"))
                .setHeader("Content-Type", JSON_MIME_TYPE)
                .setHeader("Accept", JSON_MIME_TYPE);
    }

    public Catalog getCatalog() throws IOException {
        return mapper.readValue(landingPage, CatalogImpl.class);
    }

    public Optional<Collection> getCollection(String id) throws IOException {
        URL url = new URL(
                this.landingPage,
                String.format("collections/%s", id)
        );
        try {
            return Optional.ofNullable(mapper.readValue(url, CollectionImpl.class));
        } catch (FileNotFoundException e){
            return Optional.empty();
        }
    }

    public Optional<Item> getItem(String collectionId, String itemId) throws IOException {
        URL url = new URL(
                this.landingPage,
                String.format("collections/%s/items/%s", collectionId, itemId)
        );
        try {
            return Optional.ofNullable(mapper.readValue(url, ItemImpl.class));
        } catch (FileNotFoundException e){
            return Optional.empty();
        }
    }

    public ItemCollection search(QueryParameter parameter) throws IOException, InterruptedException {
        String body = mapper.writeValueAsString(parameter);

        HttpRequest request = searchReqBuilder
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), ItemCollectionImpl.class);
    }
}
