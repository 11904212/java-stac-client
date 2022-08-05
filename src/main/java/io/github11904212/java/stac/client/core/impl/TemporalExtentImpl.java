package io.github11904212.java.stac.client.core.impl;

import io.github11904212.java.stac.client.core.TemporalExtent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TemporalExtentImpl implements TemporalExtent {


    private final List<List<String>> interval;

    @JsonCreator
    public TemporalExtentImpl(
            @JsonProperty("interval") List<List<String>> interval
    ) {
        this.interval = Objects.requireNonNullElse(interval, Collections.emptyList());
    }

    @Override
    public List<List<String>> getInterval() {
        return interval;
    }
}
