package io.github11904212.java.stac.client.core;

import java.util.List;

/**
 * A spatial-extent contains information about the spatial extents of a {@link Collection}.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec/collection-spec.md#spatial-extent-object">spatial-extent-spec</a>
 */
public interface SpatialExtent {

    /**
     * a list of bounding boxes covered by the {@link Collection}
     * @return the list of bounding boxes.
     */
    List<List<Double>> getBbox();
}
