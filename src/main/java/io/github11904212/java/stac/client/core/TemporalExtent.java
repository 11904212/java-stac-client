package io.github11904212.java.stac.client.core;

import java.util.List;

/**
 * A temporal-extent contains information about the temporal extents of a {@link Collection}.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec/collection-spec.md#temporal-extent-object">temporal-extent-spec</a>
 */
public interface TemporalExtent {

    /**
     * a list of time intervals covered by the {@link Collection}
     * @return the list of intervals.
     */
    List<List<String>> getInterval();
}
