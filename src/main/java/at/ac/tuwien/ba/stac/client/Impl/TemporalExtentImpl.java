package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.TemporalExtent;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.List;

public class TemporalExtentImpl implements TemporalExtent {

    private final JsonObject content;

    public TemporalExtentImpl(JsonObject content) {
        this.content = content;
    }

    @Override
    public List<List<LocalDateTime>> getInterval() {
        // TODO
        return null;
    }
}
