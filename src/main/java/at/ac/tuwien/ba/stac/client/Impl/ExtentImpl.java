package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.Extent;
import at.ac.tuwien.ba.stac.client.SpatialExtent;
import at.ac.tuwien.ba.stac.client.TemporalExtent;
import com.google.gson.JsonObject;

public class ExtentImpl extends GsonWrapper implements Extent {

    public ExtentImpl(JsonObject content) {
        super(content);
    }

    @Override
    public SpatialExtent getSpatial() {
        return new SpatialExtentImpl(this.content.get("spatial").getAsJsonObject());
    }

    @Override
    public TemporalExtent getTemporal() {
        return new TemporalExtentImpl(this.content.get("temporal").getAsJsonObject());
    }
}
