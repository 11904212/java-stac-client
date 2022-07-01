package at.ac.tuwien.ba.stac.client.search.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import mil.nga.sf.geojson.FeatureConverter;
import mil.nga.sf.wkt.GeometryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueryParameterTest {

    private static final String DATE_START = "2018-02-12T00:00:00Z";
    private static final String DATE_END = "2018-03-18T12:31:12Z";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

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

        var datetime = DATE_START + "/" + DATE_END;
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

        assertThat(res)
                .contains(String.format("\"datetime\":\"%s\"", datetime))
                .contains(String.format("\"field\":\"%s\"", sortByKey))
                .contains("\"direction\":\"desc\"")
                .contains(String.format("\"collections\":[\"%s\"]", collectionId))
                .contains(String.format("\"ids\":[\"%s\"]", itemId))
                .contains(String.format("\"limit\":%d", limit))
                .contains(geomJson)
                .contains(Arrays.toString(bbox).replace(" ", ""));
    }

    @Test
    public void itShould_SerializeSingleDateTime() throws Exception{

        QueryParameter parameter = new QueryParameter();
        ZonedDateTime dateTime = ZonedDateTime.parse(DATE_START, DATE_FORMATTER);

        parameter.setDatetime(dateTime);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res).contains(String.format("\"datetime\":\"%s\"", DATE_START));

    }

    @Test
    public void itShould_SerializeClosedInterval() throws Exception{

        QueryParameter parameter = new QueryParameter();
        ZonedDateTime start = ZonedDateTime.parse(DATE_START, DATE_FORMATTER);
        ZonedDateTime end = ZonedDateTime.parse(DATE_END, DATE_FORMATTER);

        parameter.setDatetimeInterval(start, end);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res).contains(String.format("\"datetime\":\"%s/%s\"", DATE_START, DATE_END));
    }

    @Test
    public void itShouldFail_WhenStartAndEndNull(){

        QueryParameter parameter = new QueryParameter();

        assertThatThrownBy(() ->
                parameter.setDatetimeInterval(null, null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itShouldFail_WhenStartAfterEnd(){

        QueryParameter parameter = new QueryParameter();
        ZonedDateTime end = ZonedDateTime.parse(DATE_END, DATE_FORMATTER);
        ZonedDateTime start = end.plus(1, ChronoUnit.SECONDS);

        assertThatThrownBy(() ->
                parameter.setDatetimeInterval(start, end)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itShould_SerializeLeftOpenInterval() throws Exception{

        QueryParameter parameter = new QueryParameter();
        ZonedDateTime end = ZonedDateTime.parse(DATE_END, DATE_FORMATTER);

        parameter.setDatetimeInterval(null, end);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res).contains(String.format("\"datetime\":\"%s/%s\"", "..", DATE_END));
    }

    @Test
    public void itShould_SerializeRightOpenInterval() throws Exception{

        QueryParameter parameter = new QueryParameter();
        ZonedDateTime start = ZonedDateTime.parse(DATE_START, DATE_FORMATTER);

        parameter.setDatetimeInterval(start, null);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res).contains(String.format("\"datetime\":\"%s/%s\"", DATE_START, ".."));
    }

    @Test
    public void itShould_Serialize_Fields() throws Exception{

        QueryParameter parameter = new QueryParameter();
        QueryParamFields fields = new QueryParamFields();
        var field = "field1";
        fields.addFieldToInclude(field);
        parameter.setFields(fields);

        String res = mapper.writeValueAsString(parameter);

        assertThat(res)
                .contains(String.format("{\"fields\":{\"include\":[\"%s\"]}}", field));
    }
}
