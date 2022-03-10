import java.util.Optional;

public interface Link {

    String getHref();

    String getRel();

    Optional<String> getType();

    Optional<String> getTitle();
}
