package com.github11904212.java.stac.client.core;

import java.util.List;
import java.util.Optional;

/**
 * A catalog is a container that holds other catalogs, collection and items as link list.
 * @see <a href="https://github.com/radiantearth/stac-spec/tree/master/catalog-spec">catalog-spec</a>
 */
public interface Catalog {

    /**
     * the type of the catalog (usually Catalog).
     * @return the type.
     */
    String getType();

    /**
     * the stac-version of the catalog.
     * @return the stac-version.
     */
    String getStacVersion();

    /**
     * a list of extensions which are implemented by this catalog.
     * @return the list of extensions.
     */
    List<String> getStacExtensions();

    /**
     * returns an identifier for this catalog.
     * @return the id.
     */
    String getId();

    /**
     * returns the optional title fo this catalog.
     * @return the title if present.
     */
    Optional<String> getTitle();

    /**
     * a detailed description for this catalog.
     * @return the description.
     */
    String getDescription();

    /**
     * a list of links references to other documents.
     * @return the list of {@link Link}s.
     */
    List<Link> getLinks();

}
