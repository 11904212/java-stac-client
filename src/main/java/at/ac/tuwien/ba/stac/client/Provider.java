package at.ac.tuwien.ba.stac.client;

import java.util.List;
import java.util.Optional;

public interface Provider {

    /** according to specification **/

    String getName();

    Optional<String> getDescription();

    List<String> getRoles();

    Optional<String> getURL();

}
