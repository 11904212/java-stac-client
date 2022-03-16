package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Extent;
import at.ac.tuwien.ba.stac.client.SpatialExtent;
import at.ac.tuwien.ba.stac.client.TemporalExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ExtentImpl implements Extent {

    private final SpatialExtent spatial;
    private final TemporalExtent temporal;

    @JsonCreator
    public ExtentImpl(
            @JsonProperty("spatial") @JsonDeserialize(as = SpatialExtentImpl.class) SpatialExtent spatial,
            @JsonProperty("temporal") @JsonDeserialize(as = TemporalExtentImpl.class) TemporalExtent temporal
    ) {
        this.spatial = null;
        this.temporal = null;
    }

    @Override
    public SpatialExtent getSpatial() {
        return spatial;
    }

    @Override
    public TemporalExtent getTemporal() {
        return temporal;
    }
}
