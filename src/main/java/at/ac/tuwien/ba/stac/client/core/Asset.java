package at.ac.tuwien.ba.stac.client.core;

import java.util.List;
import java.util.Optional;

/**
 * An asset is a container that holds information about a resource.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/item-spec/item-spec.md#asset-object">asset-spec</a>
 */
public interface Asset {

    /**
     * the URI of the resource.
     * @return the URI as String.
     */
    String getHref();


    /**
     * an optional title.
     * @return the title.
     */
    Optional<String> getTitle();

    /**
     * an optional description.
     * @return the description.
     */
    Optional<String> getDescription();

    /**
     * an optional media type.
     * @return the media type.
     */
    Optional<String> getType();

    /**
     * a list of roles describing the intended purpose of an asset.
     * @return the list of roles.
     */
    List<String> getRoles();

}
