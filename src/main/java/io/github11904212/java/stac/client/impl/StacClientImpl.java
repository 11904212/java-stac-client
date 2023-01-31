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
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class StacClientImpl implements StacClient {

    private final ObjectMapper mapper;
    private final URL landingPage;

    public StacClientImpl(URL landingPage) {
        this.landingPage = landingPage;
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Catalog getCatalog() throws IOException {
        return mapper.readValue(landingPage, CatalogImpl.class);
    }

    public Optional<Collection> getCollection(String id) throws IOException {
        String urlStr = this.landingPage + "collections/" + id;
        URL url = new URL(urlStr);
        try {
            return Optional.ofNullable(mapper.readValue(url, CollectionImpl.class));
        } catch (FileNotFoundException e){
            return Optional.empty();
        }
    }

    public Optional<Item> getItem(String collectionId, String itemId) throws IOException {
        String urlStr = String.format("%scollections/%s/items/%s",this.landingPage, collectionId, itemId);
        URL url = new URL(urlStr);
        try {
            return Optional.ofNullable(mapper.readValue(url, ItemImpl.class));
        } catch (FileNotFoundException e){
            return Optional.empty();
        }
    }

    public ItemCollection search(QueryParameter parameter) throws IOException, URISyntaxException, InterruptedException {
        String urlStr = this.landingPage + "search";
        URI uriObj = new URI(urlStr);
        String body = mapper.writeValueAsString(parameter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriObj)
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), ItemCollectionImpl.class);
    }
}
