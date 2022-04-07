package at.ac.tuwien.ba.stac.client;

import at.ac.tuwien.ba.stac.client.search.dto.QueryParameter;
import at.ac.tuwien.ba.stac.client.search.dto.SortDirection;
import com.fasterxml.jackson.databind.ObjectMapper;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.wkt.GeometryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryParameterTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
    }

    @Test
    public void itShouldSerializeQueryParameter1() throws Exception{


        Double[] bbox = {
                -110d,
                39.5d,
                -105d,
                40.5d
        };

        var datetime = "2018-02-12T00:00:00Z/2018-03-18T12:31:12Z";
        var geomWkt = "POINT(14.292675598144532 47.92422212905355)";
        var geom = FeatureConverter.toGeometry(
                GeometryReader.readGeometry(geomWkt)
        );
        var geomJson = FeatureConverter.toStringValue(geom);
        var sortByKey = "properties.eo:cloud_cover";
        var sortDir = SortDirection.DESC;
        var collectionId = "collection-id-1";
        var itemId = "item-id-1";
        var limit = 42;


        QueryParameter parameter = new QueryParameter();
        parameter.setLimit(limit);
        parameter.addCollection(collectionId);
        parameter.addSortBy(sortByKey, sortDir);
        parameter.addId(itemId);

        parameter.setBbox(bbox);
        parameter.setDatetime(datetime);
        parameter.setIntersects(geom);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res).contains(String.format("\"datetime\":\"%s\"", datetime));
        assertThat(res).contains(String.format("\"field\":\"%s\"", sortByKey));
        assertThat(res).contains("\"direction\":\"desc\"");
        assertThat(res).contains(String.format("\"collections\":[\"%s\"]", collectionId));
        assertThat(res).contains(String.format("\"ids\":[\"%s\"]", itemId));
        assertThat(res).contains(String.format("\"limit\":%d", limit));
        assertThat(res).contains(geomJson);
        assertThat(res).contains(Arrays.toString(bbox).replace(" ", ""));


    }
}
