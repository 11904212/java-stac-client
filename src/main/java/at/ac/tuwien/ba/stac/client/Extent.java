package at.ac.tuwien.ba.stac.client;

public interface Extent {

    SpatialExtent getSpatial();

    TemporalExtent getTemporal();
}
