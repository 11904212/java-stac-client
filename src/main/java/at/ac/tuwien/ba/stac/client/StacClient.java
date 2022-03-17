package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.Impl.CatalogImpl;
import at.ac.tuwien.ba.stac.client.Impl.CollectionImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class StacClient {

    private final ObjectMapper mapper;

    public StacClient () {
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Catalog open(String uri) throws IOException {
        URL url = new URL(uri);
        return mapper.readValue(url, CatalogImpl.class);
    }

    public Collection getCollection(String uri) throws IOException {
        URL url = new URL(uri);
        return mapper.readValue(url, CollectionImpl.class);
    }

    public static void main(String[] args) {

        StacClient client = new StacClient();
        Catalog catalog;
        Collection collection;

        try {
            catalog = client.open("https://planetarycomputer.microsoft.com/api/stac/v1/");

            System.out.println(catalog);
            System.out.println(catalog.getType());
            System.out.println(catalog.getStacVersion());
            System.out.println(catalog.getStacExtensions());
            System.out.println(catalog.getId());
            System.out.println(catalog.getTitle());
            System.out.println(catalog.getDescription());
            System.out.println(catalog.getLinks());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            collection = client.getCollection("https://planetarycomputer.microsoft.com/api/stac/v1/collections/sentinel-2-l2a");
            System.out.println(collection.getType());
            System.out.println(collection.getStacVersion());
            System.out.println(collection.getStacExtensions());
            System.out.println(collection.getAssets());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
