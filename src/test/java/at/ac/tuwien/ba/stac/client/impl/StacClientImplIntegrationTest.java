package at.ac.tuwien.ba.stac.client.impl;

import at.ac.tuwien.ba.stac.client.StacClient;
import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.search.dto.QueryParameter;
import com.fasterxml.jackson.databind.JsonMappingException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StacClientImplIntegrationTest {

    private MockWebServer mockStacApi;

    private StacClient stacClient;


    @BeforeEach
    void initialize() throws IOException {
        mockStacApi = new MockWebServer();
        mockStacApi.start();

        stacClient = new StacClientImpl(mockStacApi.url("/").uri().toURL());
    }

    @AfterEach
    void cleanUp() throws IOException {
        mockStacApi.shutdown();
    }

    @Test
    void getCatalog_whenResponseIsValid_expectCorrectApiInteraction() throws Exception{

        String body = readTextFromResource("examples/catalog.json");

        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        Catalog res = stacClient.getCatalog();

        assertThat(res).isNotNull();

        assertThat(mockStacApi.getRequestCount())
                .withFailMessage("only one query should be performed")
                .isEqualTo(1);

        RecordedRequest request = mockStacApi.takeRequest();
        assertThat(request.getPath())
                .withFailMessage("client used a wrong rout")
                .isEqualTo("/");

        assertThat(request.getMethod())
                .withFailMessage("client did not use a GET request")
                .isEqualTo("GET");

    }

    @Test
    void getCatalog_whenResponseIsInvalid_expectException() {

        String body = "{}";

        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        assertThatThrownBy(() -> stacClient.getCatalog()).isInstanceOf(JsonMappingException.class);

    }

    @Test
    void getCatalog_whenServerReturnsError_expectException() {

        mockStacApi.enqueue(new MockResponse().setResponseCode(500));

        assertThatThrownBy(() -> stacClient.getCatalog()).isInstanceOf(IOException.class);

    }

    @Test
    void getCatalog_whenNotFound_expectException() {

        mockStacApi.enqueue(new MockResponse().setResponseCode(404));

        assertThatThrownBy(() -> stacClient.getCatalog())
                .isInstanceOf(IOException.class)
        ;

    }

    @Test
    void getCollection_whenResponseIsValid_expectCorrectApiInteraction() throws Exception{

        String collectionId = "sentinel";

        String body = readTextFromResource("examples/collection.json");
        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        Optional<Collection> res = stacClient.getCollection(collectionId);

        assertThat(res).isNotEmpty();

        assertThat(mockStacApi.getRequestCount())
                .withFailMessage("only one query should be performed")
                .isEqualTo(1);

        RecordedRequest request = mockStacApi.takeRequest();
        assertThat(request.getPath())
                .withFailMessage("client used a wrong rout")
                .isEqualTo(String.format("/collections/%s", collectionId));

        assertThat(request.getMethod())
                .withFailMessage("client did not use a GET request")
                .isEqualTo("GET");

    }

    @Test
    void getCollection_whenResponseIsInvalid_expectException() {

        mockStacApi.enqueue(new MockResponse()
                .setBody("{}")
                .addHeader("Content-Type", "application/json"));

        assertThatThrownBy(() -> stacClient.getCollection("42"))
                .isInstanceOf(JsonMappingException.class);

    }

    @Test
    void getCollection_whenNotFound_expectEmptyOptional() throws Exception {

        mockStacApi.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody("{\"code\":\"NotFoundError\",\"description\":\"No collection with id 'hans' found!\"}")
                .addHeader("Content-Type", "application/json"));

        Optional<Collection> res = stacClient.getCollection("42");
        assertThat(res).isEmpty();

    }

    @Test
    void getItem_whenResponseIsValid_expectCorrectApiInteraction() throws Exception{


        String collectionId = "sentinel";
        String featureId = "1234";

        String body = readTextFromResource("examples/item1.json");
        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        stacClient.getItem(collectionId, featureId);

        assertThat(mockStacApi.getRequestCount())
                .withFailMessage("only one query should be performed")
                .isEqualTo(1);

        RecordedRequest request = mockStacApi.takeRequest();
        assertThat(request.getPath())
                .withFailMessage("client used a wrong rout")
                .isEqualTo(String.format("/collections/%s/items/%s", collectionId, featureId));

        assertThat(request.getMethod())
                .withFailMessage("client did not use a GET request")
                .isEqualTo("GET");

    }

    @Test
    void getItem_whenResponseIsInvalid_expectException() throws Exception {

        String collectionId = "sentinel";
        String featureId = "1234";

        String body = readTextFromResource("examples/item-invalid.json");
        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        assertThatThrownBy(() -> stacClient.getItem(collectionId, featureId))
                .isInstanceOf(JsonMappingException.class);

    }

    @Test
    void getItem_whenNotFound_expectEmptyOptional() throws Exception {

        String collectionId = "sentinel";
        String featureId = "1234";

        mockStacApi.enqueue(new MockResponse()
                        .setResponseCode(404)
                .addHeader("Content-Type", "application/json"));

        Optional<Item> res = stacClient.getItem(collectionId, featureId);

        assertThat(res).isEmpty();

    }

    @Test
    void search_whenResponseIsValid_expectCorrectApiInteraction() throws Exception{

        QueryParameter param = new QueryParameter();
        param.addId("1234");

        String body = readTextFromResource("examples/itemcollection-sample-full.json");
        mockStacApi.enqueue(new MockResponse()
                .setBody(body)
                .addHeader("Content-Type", "application/json"));

        stacClient.search(param);

        assertThat(mockStacApi.getRequestCount())
                .withFailMessage("only one query should be performed")
                .isEqualTo(1);

        RecordedRequest request = mockStacApi.takeRequest();
        assertThat(request.getPath())
                .withFailMessage("client used a wrong rout")
                .isEqualTo("/search");

        assertThat(request.getMethod())
                .withFailMessage("client did not use a POST request")
                .isEqualTo("POST");

    }

    @Test
    void search_whenResponseIsInvalid_expectException() {

        QueryParameter param = new QueryParameter();
        param.addId("1234");

        mockStacApi.enqueue(new MockResponse()
                .setBody("{}")
                .addHeader("Content-Type", "application/json"));

        assertThatThrownBy(() -> stacClient.search(param))
                .isInstanceOf(JsonMappingException.class);

    }

    private String readTextFromResource(String resource) throws IOException {
        File file = new File(ClassLoader.getSystemResource(resource).getFile());
        return Files.readString(file.toPath());
    }

}
