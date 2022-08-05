package com.github11904212.java.stac.client.core;

import java.util.List;
import java.util.Optional;

/**
 * A provider is a container that holds information about an organization that captures or processes the content of the {@link Collection}.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec/collection-spec.md#provider-object">provider-spec</a>
 */
public interface Provider {

    /**
     * returns the name of the organization or individual.
     * @return the name.
     */
    String getName();

    /**
     * an optional description about processing, processors, producers and hosting.
     * @return the description, if present.
     */
    Optional<String> getDescription();

    /**
     * returns the roles of the provider, any of [licensor, producer, processor, host].
     * @return the list of roles.
     */
    List<String> getRoles();

    /**
     * returns a link to a homepage on which the provider can provide additional information.
     * @return the URL as String, if present.
     */
    Optional<String> getURL();

}
