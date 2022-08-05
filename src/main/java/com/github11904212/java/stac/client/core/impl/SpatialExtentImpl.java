package com.github11904212.java.stac.client.core.impl;

import com.github11904212.java.stac.client.core.SpatialExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpatialExtentImpl implements SpatialExtent {

    private final List<List<Double>> bbox;

    @JsonCreator
    public SpatialExtentImpl(
            @JsonProperty(value = "bbox", required = true) List<List<Double>> bbox
    ) {
        this.bbox = bbox;
    }

    @Override
    public List<List<Double>> getBbox() {
        return bbox;
    }
}
