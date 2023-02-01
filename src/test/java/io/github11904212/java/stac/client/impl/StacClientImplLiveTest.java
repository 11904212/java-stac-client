package io.github11904212.java.stac.client.impl;

import io.github11904212.java.stac.client.StacClient;
import io.github11904212.java.stac.client.core.Catalog;
import io.github11904212.java.stac.client.core.Collection;
import io.github11904212.java.stac.client.core.Item;
import io.github11904212.java.stac.client.search.ItemCollection;
import io.github11904212.java.stac.client.search.dto.QueryParamFields;
import io.github11904212.java.stac.client.search.dto.QueryParameter;
import io.github11904212.java.stac.client.search.dto.SortDirection;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.geojson.Geometry;
import mil.nga.sf.wkt.GeometryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("use this only for manual testing")
class StacClientImplLiveTest {

    private static final String TEST_URL_MSPC = "https://planetarycomputer.microsoft.com/api/stac/v1/";
    private static final String TEST_URL_AWS = "https://earth-search.aws.element84.com/v1/";
    private static final String COLLECTION_ID = "sentinel-2-l2a";
    private static final String ITEM_ID = "S2B_MSIL2A_20220318T080649_R078_T42XWH_20220318T120719";
    private static final String WKT_AOI =
            "POLYGON((14.258 47.908,14.293 47.908,14.293 47.888,14.258 47.888,14.258 47.908))";

    private StacClient testClient;

    @BeforeEach
    public void init () throws Exception {
        URL stacEndpoint = new URL(TEST_URL_MSPC);
        this.testClient = new StacClientImpl(stacEndpoint);
    }

    @Test
    void getCatalog_shouldReturnCatalog() throws Exception {
        Catalog res = this.testClient.getCatalog();
        assertThat(res.getType()).isEqualTo("Catalog");
        assertThat(res.getId()).isEqualTo("microsoft-pc");
        assertThat(res.getStacVersion()).isEqualTo("1.0.0");
    }

    @Test
    void getCollection_shouldReturnCollection() throws Exception {
        Optional<Collection> optRes = this.testClient.getCollection(COLLECTION_ID);
        assertThat(optRes).isNotEmpty();
        Collection res = optRes.get();
        assertThat(res.getId()).isEqualTo(COLLECTION_ID);
        assertThat(res.getType()).isEqualTo("Collection");
    }

    @Test
    void getItem_shouldReturnItem() throws Exception {
        Optional<Item> optRes = this.testClient.getItem(COLLECTION_ID, ITEM_ID);
        assertThat(optRes).isNotEmpty();
        Item res = optRes.get();
        assertThat(res.getId()).isEqualTo(ITEM_ID);
        assertThat(res.getType()).isEqualTo("Feature");
    }

    @Test
    void searchItem_withLimit_shouldReturnItemCollectionOfSizeLimit() throws Exception {

        QueryParameter queryParameter = new QueryParameter();
        queryParameter.addCollection(COLLECTION_ID);
        queryParameter.setLimit(7);

        ItemCollection res = testClient.search(queryParameter);

        assertThat(res.getType()).isEqualTo("FeatureCollection");
        assertThat(res.getItems()).hasSize(7);

    }


    @Test
    void searchItem_withFieldsParam_shouldContainFields() throws Exception {

        var queryParameter = new QueryParameter();
        queryParameter.addId(ITEM_ID);
        var fieldsParam = new QueryParamFields();
        fieldsParam.addFieldToExclude("properties.datetime");
        queryParameter.setFields(fieldsParam);

        ItemCollection res = testClient.search(queryParameter);

        var cloudCover = res.getItems().get(0).getProperties().get("datetime");

        assertThat(cloudCover).isNull();

    }

    @Test
    void searchItem_withSortBy_shouldSortItems() throws Exception {

        // planetarycomputer seems to have a problem with the sorting,
        // as it returns the following list: ..., 89.252603, 9.008443, 94.83971, ...
        // short term solution -> use other STAC-server
        // long term solution -> reported bug on github https://github.com/microsoft/PlanetaryComputer/issues/81
        //
        testClient = new StacClientImpl(new URL(TEST_URL_AWS));

        var queryParameter = new QueryParameter();
        queryParameter.addSortBy("properties.eo:cloud_cover", SortDirection.ASC);
        queryParameter.addCollection("sentinel-2-l2a");
        queryParameter.setLimit(10);

        ItemCollection res = testClient.search(queryParameter);

        assertThat(res.getItems()).hasSize(10);

        var actualCoverList = res.getItems().stream().map(
                item -> Double.parseDouble(item.getProperties().get("eo:cloud_cover").toString())
        ).collect(Collectors.toList());

        var expectedList = new ArrayList<>(actualCoverList);
        Collections.sort(expectedList);

        assertThat(actualCoverList).isEqualTo(expectedList);

    }

    @Test
    void searchItem_withIntersection_shouldIntersectAoi() throws Exception {

        var aoi = convertWktToGeom(WKT_AOI);

        var queryParameter = new QueryParameter();
        queryParameter.setIntersects(aoi);
        queryParameter.setLimit(1);

        ItemCollection res = testClient.search(queryParameter);

        assertThat(res.getItems()).hasSize(1);

        var item = res.getItems().get(0);

        var envelopeItem = item.getGeometry().getGeometry().getEnvelope();
        var envelopeAoi = aoi.getGeometry().getEnvelope();

        assertThat(envelopeItem.intersects(envelopeAoi)).isTrue();

    }

    @Test
    void searchItem_withDateTimeRange_shouldContainDatetime() throws Exception {

        var intervalStart = ZonedDateTime.parse("2022-02-13T00:00:00.00Z");
        var intervalEnd = ZonedDateTime.parse("2022-04-15T23:59:59.99Z");

        var queryParameter = new QueryParameter();
        queryParameter.setLimit(1);
        queryParameter.setDatetimeInterval(intervalStart, intervalEnd);

        ItemCollection res = testClient.search(queryParameter);

        assertThat(res.getItems()).hasSize(1);

        var item = res.getItems().get(0);

        assertThat(item.getDateTime()).isNotEmpty();

        var itemDateTime = ZonedDateTime.parse(item.getDateTime().get());

        assertThat(itemDateTime)
                .isAfterOrEqualTo(intervalStart)
                .isBeforeOrEqualTo(intervalEnd);

    }

    private Geometry convertWktToGeom(String wkt) throws IOException {
        var geom = GeometryReader.readGeometry(wkt);
        return FeatureConverter.toGeometry(geom);
    }

}
