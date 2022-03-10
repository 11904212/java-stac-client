package at.ac.tuwien.ba.stac.client;

import com.sun.istack.internal.Nullable;
import org.geotools.geojson.GeoJSON;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Item {

    /** according to specification **/

    String getType();

    String getStacVersion();

    List<String> getStacExtensions();

    String getId();

    @Nullable
    GeoJSON getGeometry();

    @Nullable
    List<Double> getBbox();

    Properties getProperties();

    List<Link> getLinks();

    Map<String, Asset> getAssets();

    Optional<String> getCollection();


    /** convenience methods **/

    LocalDateTime getDateTime();

    //List<at.ac.tuwien.ba.Asset> getAssets();

    Asset getAsset(String key);

}
