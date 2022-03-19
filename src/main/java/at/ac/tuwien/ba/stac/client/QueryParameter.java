package at.ac.tuwien.ba.stac.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import mil.nga.sf.geojson.GeoJsonObject;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryParameter {

    private Integer limit;
    private Double[] bbox;
    private String datetime;
    private GeoJsonObject intersects;
    private List<String> ids = new ArrayList<>();
    private List<String> collections = new ArrayList<>();

    public QueryParameter() {
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Double[] getBbox() {
        return bbox;
    }

    public void setBbox(Double[] bbox) {
        this.bbox = bbox;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public GeoJsonObject getIntersects() {
        return intersects;
    }

    public void setIntersects(GeoJsonObject intersects) {
        this.intersects = intersects;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public void addId(String id) {
        this.ids.add(id);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getCollections() {
        return collections;
    }

    public void setCollections(List<String> collections) {
        this.collections = collections;
    }

    public void addCollection(String id) {
        this.collections.add(id);
    }
}
