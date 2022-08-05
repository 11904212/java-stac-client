package com.github11904212.java.stac.client.search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import mil.nga.sf.geojson.GeoJsonObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is a POJO containing all the possible parameter for a STAC search.
 * see https://api.stacspec.org/v1.0.0-rc.1/item-search/#operation/getItemSearch
 * for detailed information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryParameter {

    private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    private Integer limit;
    private Double[] bbox;
    private String datetime;
    private GeoJsonObject intersects;
    private List<String> ids = new ArrayList<>();
    private List<String> collections = new ArrayList<>();
    private List<SortBy> sortBy = new ArrayList<>();
    private QueryParamFields fields;


    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @JsonProperty("bbox")
    public Double[] getBbox() {
        return bbox;
    }

    public void setBbox(Double[] bbox) {
        this.bbox = bbox;
    }

    @JsonProperty("datetime")
    public String getDatetime() {
        return datetime;
    }

    /**
     * set the datetime directly. note formatting as specified in the STAC-API.
     * @param datetime as String. will not be verified.
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * set the exact datetime
     * @param datetime as {@link ZonedDateTime}
     */
    public void setDatetime(ZonedDateTime datetime) {
        this.datetime = datetime.format(DEFAULT_DATE_FORMAT);
    }

    /**
     * set an open or closed interval
     * @param start as {@link ZonedDateTime}, set to null for an open interval
     * @param end as {@link ZonedDateTime}, set to null for an open interval
     * @throws IllegalArgumentException if start and end are null, or start is after end.
     */
    public void setDatetimeInterval(ZonedDateTime start, ZonedDateTime end) {
        if (start == null && end == null) {
            throw new IllegalArgumentException("at least one datetime must be specified. please specify start and/or end.");
        }
        if (start != null && end != null && start.isAfter(end)) {
            throw new IllegalArgumentException("start date must be before the end date.");
        }
        String startStr = start != null ? start.format(DEFAULT_DATE_FORMAT) : "..";
        String endStr = end != null ? end.format(DEFAULT_DATE_FORMAT) : "..";
        this.datetime = startStr + "/" + endStr;
    }

    @JsonProperty("intersects")
    public GeoJsonObject getIntersects() {
        return intersects;
    }

    public void setIntersects(GeoJsonObject intersects) {
        this.intersects = intersects;
    }

    @JsonProperty("ids")
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

    @JsonProperty("collections")
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

    @JsonProperty("sortby")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<SortBy> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<SortBy> sortBy) {
        this.sortBy = sortBy;
    }

    public void addSortBy(SortBy sortby) {
        this.sortBy.add(sortby);
    }

    public void addSortBy(String field, SortDirection direction) {
        this.sortBy.add(new SortBy(field, direction));
    }

    @JsonProperty("fields")
    public QueryParamFields getFields() {
        return fields;
    }

    public void setFields(QueryParamFields fields) {
        this.fields = fields;
    }
}
