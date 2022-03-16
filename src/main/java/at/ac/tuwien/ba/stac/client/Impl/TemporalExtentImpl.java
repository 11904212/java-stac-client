package at.ac.tuwien.ba.stac.client.Impl;

import at.ac.tuwien.ba.stac.client.TemporalExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemporalExtentImpl implements TemporalExtent {


    //TODO mapping via jackson not working
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
