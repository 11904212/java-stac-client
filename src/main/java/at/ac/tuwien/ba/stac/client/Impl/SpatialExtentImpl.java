package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.SpatialExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SpatialExtentImpl implements SpatialExtent {

    //TODO mapping via jackson not working
    private final List<List<Double>> bbox;

    @JsonCreator
    public SpatialExtentImpl(
            @JsonProperty("bbox") List<List<Double>> bbox
    ) {
        this.bbox = bbox;
    }

    @Override
    public List<List<Double>> getBbox() {
        return bbox;
    }
}
