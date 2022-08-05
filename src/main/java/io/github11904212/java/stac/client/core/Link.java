package io.github11904212.java.stac.client.core;

import java.util.Optional;

/**
 * A link is used to describe relationships between this object and other documents.
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/catalog-spec/catalog-spec.md#link-object">catalog-link</a>
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/collection-spec/collection-spec.md#link-object">collection-link</a>
 * @see <a href="https://github.com/radiantearth/stac-spec/blob/master/item-spec/item-spec.md#link-object">item-link</a>
 */
public interface Link {

    /**
     * the URL pointing to the entity.
     * @return the URL as string.
     */
    String getHref();

    /**
     * the relationship between the current document and the linked document.
     * @return the relationship.
     */
    String getRel();

    /**
     * an optional media type of the other document.
     * @return the media type if present.
     */
    Optional<String> getType();

    /**
     * an optional title of the other document.
     * @return the title if present.
     */
    Optional<String> getTitle();
}
