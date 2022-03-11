package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.SpatialExtent;
import com.google.gson.JsonObject;

import java.util.List;

public class SpatialExtentImpl implements SpatialExtent {

    private final JsonObject content;

    public SpatialExtentImpl(JsonObject content) {
        this.content = content;
    }


    @Override
    public List<List<Double>> getBbox() {
        //TODO
        return null;
    }
}
