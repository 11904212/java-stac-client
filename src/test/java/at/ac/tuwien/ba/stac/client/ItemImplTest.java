package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.core.impl.ItemImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemImplTest {
    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void itShouldDeserializeItem1() throws Exception{
        URL url = ClassLoader.getSystemResource("examples/core-item.json");
        Item res = mapper.readValue(url, ItemImpl.class);

        assertThat(res.getType()).isEqualTo("Feature");
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
        assertThat(res.getStacExtensions().size()).isEqualTo(0);
        assertThat(res.getId()).isEqualTo("20201211_223832_CS2");
        assertThat(res.getGeometry()).isNotNull();
        assertThat(res.getBbox()).contains(
                172.91173669923782,
                1.3438851951615003,
                172.95469614953714,
                1.3690476620161975);
        assertThat((String) res.getProperties().get("mission")).isEqualTo("collection 5624");
        assertThat(res.getLinks().size()).isEqualTo(4);
        assertThat(res.getAssets().size()).isEqualTo(6);
        assertThat(res.getAssets().get("visual").getHref())
                .isEqualTo("https://storage.googleapis.com/open-cogs/stac-examples/20201211_223832_CS2.tif");
        assertThat(res.getCollection().isPresent()).isTrue();
        assertThat(res.getCollection().get()).isEqualTo("simple-collection");

    }
}
