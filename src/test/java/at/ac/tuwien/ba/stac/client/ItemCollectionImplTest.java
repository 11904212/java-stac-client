package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.search.ItemCollection;
import at.ac.tuwien.ba.stac.client.search.impl.ItemCollectionImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemCollectionImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void itShouldDeserializeItemCollection1() throws Exception{
        URL url = ClassLoader.getSystemResource("examples/itemcollection-sample-full.json");
        ItemCollection res = mapper.readValue(url, ItemCollectionImpl.class);
        assertThat(res.getType()).isEqualTo("FeatureCollection");
        assertThat(res.getItems().size()).isEqualTo(1);
        assertThat(res.getItems().get(0).getAssets().get("analytic").getHref())
                .isEqualTo("https://stac-api.example.com/catalog/CS3-20160503_132130_04/analytic.tif");
    }
}
