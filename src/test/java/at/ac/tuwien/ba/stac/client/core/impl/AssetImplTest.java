package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.Asset;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssetImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void mapAsset_whenFullJsonValid_expectCorrectProperties() throws Exception{
        String expectedHref = "./some/rout";
        String expectedTitle = "LinkTitle";
        String expectedRole = "data";
        String exceptedType = "application/json";
        String exceptedDescription = "someText";
        String jsonTemplate = "{" +
                "\"href\": \"%s\"," +
                "\"type\": \"%s\"," +
                "\"title\": \"%s\"," +
                "\"description\": \"%s\"," +
                "\"roles\": [" +
                "\"%s\"" +
                "]}";

        String json = String.format(jsonTemplate, expectedHref, exceptedType, expectedTitle, exceptedDescription, expectedRole);

        Asset res = mapper.readValue(json, AssetImpl.class);

        assertThat(res.getHref()).isEqualTo(expectedHref);
        assertThat(res.getType()).contains(exceptedType);
        assertThat(res.getTitle()).contains(expectedTitle);
        assertThat(res.getDescription()).contains(exceptedDescription);
        assertThat(res.getRoles()).hasSize(1);
        assertThat(res.getRoles().get(0)).isEqualTo(expectedRole);

        assertThat(res.toString()).contains(expectedHref);

    }

    @Test
    void mapAsset_whenMinJsonValid_expectCorrectProperties() throws Exception{
        String expectedHref = "./some/rout";
        String jsonTemplate = "{" +
                "\"href\": \"%s\"" +
                "}";

        String json = String.format(jsonTemplate, expectedHref);

        Asset res = mapper.readValue(json, AssetImpl.class);

        assertThat(res.getHref()).isEqualTo(expectedHref);
        assertThat(res.getType()).isEmpty();
        assertThat(res.getTitle()).isEmpty();
        assertThat(res.getDescription()).isEmpty();
        assertThat(res.getRoles()).isEmpty();

    }
}
