import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Collection {

    /** according to specification **/

    String getType();

    String getStacVersion();

    List<String> getStacExtensions();

    String getId();

    Optional<String> getTitle();

    String getDescription();

    List<String> getKeywords();

    String getLicense();

    List<Provider> getProviders();

    Extent getExtend();

    //TODO: summaries

    List<Link> getLinks();

    Map<String, Asset> getAssets();

    /** convenience methods **/

    List<Item> getItems();

}
