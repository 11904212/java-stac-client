import java.util.List;
import java.util.Optional;

public interface Provider {

    /** according to specification **/

    String getName();

    Optional<String> getDescription();

    Optional<List<String>> getRoles();

    Optional<String> getURL();

}
