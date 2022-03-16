package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.Impl.CatalogImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class StacClient {

    static public Catalog open(String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL url = new URL(uri);
        return objectMapper.readValue(url, CatalogImpl.class);
        
    }

    public static void main(String[] args) {

        try {
            Catalog catalog = open("https://planetarycomputer.microsoft.com/api/stac/v1/");

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
    }
}
