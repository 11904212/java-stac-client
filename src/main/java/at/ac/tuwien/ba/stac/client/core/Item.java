package at.ac.tuwien.ba.stac.client.core;

import mil.nga.sf.geojson.Geometry;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Item {

    /** according to specification **/

    String getType();

    String getStacVersion();

    List<String> getStacExtensions();

    String getId();

    Geometry getGeometry();

    double[] getBbox();

    Map<String, Object> getProperties();

    List<Link> getLinks();

    Map<String, Asset> getAssets();

    Optional<String> getCollection();


    /** convenience methods **/

    Optional<String> getDateTime();

    //List<at.ac.tuwien.ba.Asset> getAssets();

    Optional<Asset> getAsset(String key);

}
