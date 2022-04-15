package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.impl.CollectionImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void itShouldDeserializeCollection1() throws Exception{
        URL url = ClassLoader.getSystemResource("examples/collection.json");
        Collection res = mapper.readValue(url, CollectionImpl.class);
        assertThat(res.getId()).isEqualTo("simple-collection");
        assertThat(res.getType()).isEqualTo("Collection");
        assertThat(res.getStacExtensions().size()).isEqualTo(3);
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
        assertThat(res.getDescription()).isEqualTo("A simple collection demonstrating core catalog fields with links to a couple of items");
        assertThat(res.getTitle().isPresent()).isTrue();
        assertThat(res.getTitle().get()).isEqualTo("Simple Example Collection");
        assertThat(res.getProviders().size()).isEqualTo(1);
        assertThat(res.getProviders().get(0).getName()).isEqualTo("Remote Data, Inc");
        assertThat(res.getExtend().getSpatial()).isNotNull();
        assertThat(res.getExtend().getTemporal()).isNotNull();
        assertThat(res.getLicense()).isEqualTo("CC-BY-4.0");
        assertThat(res.getLinks().size()).isEqualTo(5);
        assertThat(res.getAssets().size()).isEqualTo(0);
        assertThat(res.getKeywords().size()).isEqualTo(0);
    }

}
