package com.github11904212.java.stac.client.core.impl;

import com.github11904212.java.stac.client.core.Link;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class LinkImplTest {

    private ObjectMapper mapper;

    @BeforeEach
    public void init(){
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    void mapLink_whenFullJsonValid_expectCorrectProperties() throws Exception{
        String expectedHref = "./some/rout";
        String expectedTitle = "LinkTitle";
        String expectedRel = "root";
        String exceptedType = "application/json";
        String jsonTemplate = "{" +
                "\"rel\": \"%s\"," +
                "\"href\": \"%s\"," +
                "\"type\": \"%s\"," +
                "\"title\": \"%s\"" +
                "}";

        String json = String.format(jsonTemplate, expectedRel, expectedHref, exceptedType, expectedTitle);

        Link res = mapper.readValue(json, LinkImpl.class);

        assertThat(res.getHref()).isEqualTo(expectedHref);
        assertThat(res.getTitle()).contains(expectedTitle);
        assertThat(res.getRel()).isEqualTo(expectedRel);
        assertThat(res.getType()).contains(exceptedType);

        assertThat(res.toString()).contains(expectedHref, expectedRel);

    }

    @Test
    void mapLink_whenMinJsonValid_expectCorrectProperties() throws Exception{
        String expectedHref = "./some/rout";
        String expectedRel = "root";
        String jsonTemplate = "{" +
                "\"rel\": \"%s\"," +
                "\"href\": \"%s\"" +
                "}";

        String json = String.format(jsonTemplate, expectedRel, expectedHref);

        Link res = mapper.readValue(json, LinkImpl.class);

        assertThat(res.getHref()).isEqualTo(expectedHref);
        assertThat(res.getTitle()).isEmpty();
        assertThat(res.getRel()).isEqualTo(expectedRel);
        assertThat(res.getType()).isEmpty();

    }
}
