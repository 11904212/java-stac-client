package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.core.Catalog;
import at.ac.tuwien.ba.stac.client.core.Collection;
import at.ac.tuwien.ba.stac.client.core.Item;
import at.ac.tuwien.ba.stac.client.impl.StacClientImpl;
import at.ac.tuwien.ba.stac.client.search.ItemCollection;
import at.ac.tuwien.ba.stac.client.search.dto.QueryParamFields;
import at.ac.tuwien.ba.stac.client.search.dto.QueryParameter;
import at.ac.tuwien.ba.stac.client.search.dto.SortDirection;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.wkt.GeometryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("use this only for manual testing")
public class StacClientImplTest {

    private static final String TEST_URL = "https://planetarycomputer.microsoft.com/api/stac/v1/";
    private static final String COLLECTION_ID = "sentinel-2-l2a";
    private static final String ITEM_ID = "S2B_MSIL2A_20220318T080649_R078_T42XWH_20220318T120719";
    private static final String WKT_AOI =
            "POLYGON((14.258 47.908,14.293 47.908,14.293 47.888,14.258 47.888,14.258 47.908))";

    private StacClient testClient;

    @BeforeEach
    public void init () throws Exception {
        URL stacEndpoint = new URL(TEST_URL);
        this.testClient = new StacClientImpl(stacEndpoint);
    }

    @Test
    public void getCatalog_shouldReturnCatalog() throws Exception {
        Catalog res = this.testClient.getCatalog();
        assertThat(res.getType()).isEqualTo("Catalog");
        assertThat(res.getId()).isEqualTo("microsoft-pc");
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
    }

    @Test
    public void getCollection_shouldReturnCollection() throws Exception {
        Collection res = this.testClient.getCollection(COLLECTION_ID);
        assertThat(res.getId()).isEqualTo(COLLECTION_ID);
        assertThat(res.getType()).isEqualTo("Collection");
    }

    @Test
    public void getItem_shouldReturnItem() throws Exception {
        Item res = this.testClient.getItem(COLLECTION_ID, ITEM_ID);
        assertThat(res.getId()).isEqualTo(ITEM_ID);
        assertThat(res.getType()).isEqualTo("Feature");
    }

    @Test
    public void searchItem_shouldReturnItemCollection() throws Exception {

        QueryParameter queryParameter = new QueryParameter();
        queryParameter.addCollection(COLLECTION_ID);
        queryParameter.setDatetime("2022-02-13/2022-04-15");

        var geom = GeometryReader.readGeometry(WKT_AOI);
        queryParameter.setIntersects(
                FeatureConverter.toGeometry(geom)
        );

        queryParameter.addSortBy("datetime", SortDirection.DESC);
        queryParameter.setLimit(2);

        ItemCollection res = testClient.search(queryParameter);

        assertThat(res.getType()).isEqualTo("FeatureCollection");
        assertThat(res.getItems()).hasSize(2);

    }


    @Test
    public void searchItem_withFieldsParam_shouldContainFields() throws Exception {

        var queryParameter = new QueryParameter();
        queryParameter.addId(ITEM_ID);
        var fieldsParam = new QueryParamFields();
        fieldsParam.addFieldToExclude("properties.datetime");
        queryParameter.setFields(fieldsParam);

        ItemCollection res = testClient.search(queryParameter);

        var cloudCover = res.getItems().get(0).getProperties().get("datetime");

        assertThat(cloudCover).isNull();

    }
}
