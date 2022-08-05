package at.ac.tuwien.ba.stac.client.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A collection is a container that holds related items.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec">collection-spec</a>
 */
public interface Collection {

    /**
     * the type of the collection (usually Collection).
     * @return the type.
     */
    String getType();

    /**
     * the stac-version of the collection.
     * @return the stac-version.
     */
    String getStacVersion();

    /**
     * a list of extensions which are implemented by this collection.
     * @return the list of extensions.
     */
    List<String> getStacExtensions();

    /**
     * returns an identifier for this collection.
     * @return the id.
     */
    String getId();

    /**
     * returns the optional title fo this collection.
     * @return the title if present.
     */
    Optional<String> getTitle();

    /**
     * a detailed description for this collection.
     * @return the description.
     */
    String getDescription();

    /**
     * a list of keywords describing this collection.
     * @return the list of keywords.
     */
    List<String> getKeywords();

    /**
     * the license under which this collection has been published.
     * @return the license.
     */
    String getLicense();

    /**
     * chronological list of all providers involved in the processing and provision of this collection.
     * @return the list of {@link Provider}
     */
    List<Provider> getProviders();

    /**
     * the extent covered by this collection.
     * @return the {@link Extent}
     */
    Extent getExtent();

    //TODO: summaries

    /**
     * a list of links references to other documents.
     * @return the list of {@link Link}s.
     */
    List<Link> getLinks();

    /**
     * a dictionary of assets of this collection.
     * @return the map of {@link Asset}s.
     */
    Map<String, Asset> getAssets();

}
