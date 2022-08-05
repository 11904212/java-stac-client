package io.github11904212.java.stac.client.core.impl;

import io.github11904212.java.stac.client.core.SpatialExtent;
import io.github11904212.java.stac.client.core.Extent;
import io.github11904212.java.stac.client.core.TemporalExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ExtentImpl implements Extent {

    private final SpatialExtent spatial;
    private final TemporalExtent temporal;

    @JsonCreator
    public ExtentImpl(
            @JsonProperty(value = "spatial", required = true) @JsonDeserialize(as = SpatialExtentImpl.class)
                    SpatialExtent spatial,

            @JsonProperty(value = "temporal", required = true) @JsonDeserialize(as = TemporalExtentImpl.class)
                    TemporalExtent temporal
    ) {
        this.spatial = spatial;
        this.temporal = temporal;
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
