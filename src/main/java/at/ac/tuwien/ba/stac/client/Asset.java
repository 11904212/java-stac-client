package at.ac.tuwien.ba.stac.client;

import java.util.List;
import java.util.Optional;

public interface Asset {

    String getHref();

    Optional<String> getTitle();

    Optional<String> getDescription();

    Optional<String> getType();

    List<String> getRoles();

}
