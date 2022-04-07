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
import at.ac.tuwien.ba.stac.client.search.dto.SortDirection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mil.nga.sf.geojson.FeatureCollection;

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

    public static void main(String[] args) {

        try {
            URL stacEndpoint = new URL("https://planetarycomputer.microsoft.com/api/stac/v1/");
            //URL stacEndpoint = new URL("https://sentinel-stac.s3.amazonaws.com/catalog.json");
            StacClientImpl client = new StacClientImpl(stacEndpoint);
            Catalog catalog;
            Collection collection;
            Item item;

            catalog = client.getCatalog();
            System.out.println(catalog);


            collection = client.getCollection("sentinel-2-l2a");
            System.out.println(collection);

            item = client.getItem("sentinel-2-l2a", "S2B_MSIL2A_20220318T080649_R078_T42XWH_20220318T120719");
            System.out.println(item);

            QueryParameter queryParameter = new QueryParameter();
            queryParameter.addCollection("sentinel-2-l2a");
            queryParameter.setDatetime("2022-02-13/2022-04-15");
            String polygonStr = "{\n" +
                    "  \"type\": \"FeatureCollection\",\n" +
                    "  \"features\": [\n" +
                    "    {\n" +
                    "      \"type\": \"Feature\",\n" +
                    "      \"properties\": {},\n" +
                    "      \"geometry\": {\n" +
                    "        \"type\": \"Polygon\",\n" +
                    "        \"coordinates\": [\n" +
                    "          [\n" +
                    "            [\n" +
                    "              15.447721481323242,\n" +
                    "              48.2544340495978\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.447839498519897,\n" +
                    "              48.25395900359276\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.448188185691832,\n" +
                    "              48.25306604802908\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.448397397994995,\n" +
                    "              48.252240943226624\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.448821187019348,\n" +
                    "              48.25243739795865\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.449244976043701,\n" +
                    "              48.252480260708985\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.449266433715819,\n" +
                    "              48.25255527043557\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.4490464925766,\n" +
                    "              48.252991039051764\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.448815822601318,\n" +
                    "              48.25367325950906\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.44850468635559,\n" +
                    "              48.25474836332529\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.447909235954285,\n" +
                    "              48.25445190838409\n" +
                    "            ],\n" +
                    "            [\n" +
                    "              15.447721481323242,\n" +
                    "              48.2544340495978\n" +
                    "            ]\n" +
                    "          ]\n" +
                    "        ]\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            FeatureCollection featureCollection = new ObjectMapper().readValue(polygonStr, FeatureCollection.class);
            queryParameter.setIntersects(
                    featureCollection.getFeature(0).getGeometry()
            );

            queryParameter.addSortBy("datetime", SortDirection.DESC);

            ItemCollection searchRes = client.search(queryParameter);
            for (Item itemRes : searchRes.getItems()){
                System.out.println(itemRes);
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
