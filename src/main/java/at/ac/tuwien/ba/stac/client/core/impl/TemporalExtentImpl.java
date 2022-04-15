package at.ac.tuwien.ba.stac.client.core.impl;

import at.ac.tuwien.ba.stac.client.core.TemporalExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemporalExtentImpl implements TemporalExtent {


    private final List<List<String>> interval;

    @JsonCreator
    public TemporalExtentImpl(
            @JsonProperty("interval") List<List<String>> interval
    ) {
        this.interval = interval;
    }

    @Override
    public List<List<String>> getInterval() {
        return interval;
    }
}
