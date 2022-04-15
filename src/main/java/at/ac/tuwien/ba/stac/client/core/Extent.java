package at.ac.tuwien.ba.stac.client.core;

public interface Extent {

    SpatialExtent getSpatial();

    TemporalExtent getTemporal();
}
