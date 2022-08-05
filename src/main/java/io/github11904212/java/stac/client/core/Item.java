package io.github11904212.java.stac.client.core;

import mil.nga.sf.geojson.Geometry;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An item is a supertype of an OGC feature and extends it with metadata and assets of a scene.
 * @see <a href="https://github.com/radiantearth/stac-spec/tree/master/item-spec">item-spec</a>
 */
public interface Item {

    /* according to specification */

    /**
     * the type of the item (usually feature).
     * @return the type.
     */
    String getType();

    /**
     * the stac-version of the item.
     * @return the stac-version.
     */
    String getStacVersion();

    /**
     * a list of extensions which are implemented by this item.
     * @return the list of extensions.
     */
    List<String> getStacExtensions();

    /**
     * an id which is unique in the associated collection of the item.
     * @return the id.
     */
    String getId();

    /**
     * the footprint of the assets which are included in this item.
     * @return the footprint as {@link mil.nga.sf.geojson.Geometry}, or null
     */
    Geometry getGeometry();

    /**
     * the bounding box of the footprint, if present.
     * @return the bounding box.
     */
    double[] getBbox();

    /**
     * a dictionary of additional metadata for the item.
     * @return a {@link Map} of properties.
     */
    Map<String, Object> getProperties();

    /**
     * a list of links related to this item.
     * @return the list of {@link Link}s.
     */
    List<Link> getLinks();

    /**
     * a dictionary of assets for the item.
     * @return the map of {@link Asset}s.
     */
    Map<String, Asset> getAssets();

    /**
     * the collection which contains this item.
     * @return the {@link Collection} if available.
     */
    Optional<String> getCollection();


    /* convenience methods */

    /**
     * the datetime of this item.
     * this method is not part of the default spec.
     * @return the datetime as string.
     */
    Optional<String> getDateTime();

    /**
     * returns asset matching the key, if available.
     * this method is not part of the default spec.
     * @param key the asset-key.
     * @return the available {@link Asset} or an empty optional.
     */
    Optional<Asset> getAsset(String key);

}
