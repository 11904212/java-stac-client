package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.impl.CatalogImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void itShouldDeserializeCatalog() throws Exception{
        URL url = ClassLoader.getSystemResource("examples/catalog.json");
        Catalog res = mapper.readValue(url, CatalogImpl.class);
        assertThat(res.getId()).isEqualTo("examples");
        assertThat(res.getType()).isEqualTo("Catalog");
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
        assertThat(res.getDescription()).isEqualTo("This catalog is a simple demonstration of an example catalog that is used to organize a hierarchy of collections and their items.");
        assertThat(res.getLinks().size()).isEqualTo(6);
    }
}
