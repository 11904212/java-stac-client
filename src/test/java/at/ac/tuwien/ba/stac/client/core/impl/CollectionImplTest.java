package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.Collection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void mapCollection_whenJsonIsValid_expectCorrectProperties() throws Exception{
        URL url = ClassLoader.getSystemResource("examples/collection.json");
        Collection res = mapper.readValue(url, CollectionImpl.class);
        assertThat(res.getId()).isEqualTo("simple-collection");
        assertThat(res.getType()).isEqualTo("Collection");
        assertThat(res.getStacExtensions()).hasSize(3);
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
        assertThat(res.getDescription()).isEqualTo("A simple collection demonstrating core catalog fields with links to a couple of items");
        assertThat(res.getTitle()).isPresent();
        assertThat(res.getTitle()).contains("Simple Example Collection");
        assertThat(res.getProviders()).hasSize(1);
        assertThat(res.getProviders().get(0).getName()).isEqualTo("Remote Data, Inc");
        assertThat(res.getExtent().getSpatial()).isNotNull();
        assertThat(res.getExtent().getTemporal()).isNotNull();
        assertThat(res.getLicense()).isEqualTo("CC-BY-4.0");
        assertThat(res.getLinks()).hasSize(5);
        assertThat(res.getAssets()).isEmpty();
        assertThat(res.getKeywords()).isEmpty();
    }

}
