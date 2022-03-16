package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.Impl.CatalogImpl;
import at.ac.tuwien.ba.stac.client.Impl.CatalogImplPOJO;
import at.ac.tuwien.ba.stac.client.Impl.CollectionImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.geotools.geojson.GeoJSON;
import org.geotools.geojson.GeoJSONUtil;
import org.geotools.geojson.geom.GeometryJSON;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

    public static Collection getCollection(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jobject = JsonParser.parseString(response.body()).getAsJsonObject();

        return new CollectionImpl(jobject);
    }

    public static void getItem(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        GeometryJSON gjson = new GeometryJSON();

        Reader reader = new StringReader(response.body());
        Geometry p = gjson.readGeometryCollection( reader );

    }

    static public Catalog open2(String uri) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        URL url = new URL(uri);
        return objectMapper.readValue(url, CatalogImplPOJO.class);
        
    }

    public static void main(String[] args) {
/*        try {
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

        try {
            Collection collection = getCollection("https://planetarycomputer.microsoft.com/api/stac/v1/collections/sentinel-2-l2a");
            System.out.println(collection.getType());
            System.out.println(collection.getStacVersion());
            System.out.println(collection.getStacExtensions());
            System.out.println(collection.getAssets());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            getItem("https://planetarycomputer.microsoft.com/api/stac/v1/collections/sentinel-2-l2a/items/S2A_MSIL2A_20220311T141011_R053_T25XFA_20220312T073622");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            Catalog catalog = open2("https://planetarycomputer.microsoft.com/api/stac/v1/");

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
