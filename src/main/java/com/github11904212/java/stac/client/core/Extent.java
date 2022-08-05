package com.github11904212.java.stac.client.core;

/**
 * An extent is a container that holds spatial and temporal information of a {@link Collection}.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec/collection-spec.md#extent-object">extent-spec</a>
 */
public interface Extent {

    /**
     * the spatial extent.
     * @return a {@link SpatialExtent}.
     */
    SpatialExtent getSpatial();

    /**
     * the temporal extent.
     * @return a {@link TemporalExtent}.
     */
    TemporalExtent getTemporal();
}
