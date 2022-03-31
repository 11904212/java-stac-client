package at.ac.tuwien.ba.stac.client;

import java.util.List;
import java.util.Optional;

public interface Catalog {

    /** according to specification **/

    String getType();

    String getStacVersion();

    List<String> getStacExtensions();

    String getId();

    Optional<String> getTitle();

    String getDescription();

    List<Link> getLinks();

}
