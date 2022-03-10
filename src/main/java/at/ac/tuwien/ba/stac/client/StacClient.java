package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.Impl.CatalogImpl;
import org.geotools.geojson.GeoJSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StacClient {

    static public Catalog open(String uri) throws IOException, InterruptedException, URISyntaxException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject resJson = (JSONObject) new JSONParser().parse(response.body());

        return new CatalogImpl(resJson);
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
        } catch (IOException | InterruptedException | URISyntaxException | ParseException e) {
            e.printStackTrace();
        }
    }
}
