package at.ac.tuwien.ba.stac.client.impl;

import at.ac.tuwien.ba.stac.client.StacClient;
import at.ac.tuwien.ba.stac.client.core.impl.CatalogImpl;
import at.ac.tuwien.ba.stac.client.core.impl.CollectionImpl;
import at.ac.tuwien.ba.stac.client.search.ItemCollection;
import at.ac.tuwien.ba.stac.client.search.impl.ItemCollectionImpl;
import at.ac.tuwien.ba.stac.client.core.impl.ItemImpl;
import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.search.dto.QueryParameter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    public Collection getCollection(String id) throws IOException {
        String urlStr = this.landingPage + "collections/" + id;
        URL url = new URL(urlStr);
        return mapper.readValue(url, CollectionImpl.class);
    }

    public Item getItem(String collectionId, String itemId) throws IOException {
        String urlStr = String.format("%scollections/%s/items/%s",this.landingPage, collectionId, itemId);
        URL url = new URL(urlStr);
        return mapper.readValue(url, ItemImpl.class);
    }

    public ItemCollection search(QueryParameter parameter) throws IOException, URISyntaxException, InterruptedException {
        String urlStr = this.landingPage + "search";
        URI uriObj = new URI(urlStr);
        String body = mapper.writeValueAsString(parameter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriObj)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), ItemCollectionImpl.class);
    }
}
