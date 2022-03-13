package at.ac.tuwien.ba.stac.client;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CommonMetadata {

    Optional<String> getTitle();

    Optional<String> getDescrition();

    Optional<LocalDateTime> getDatetime();

    Optional<LocalDateTime> getCreated();

}
