package com.dwasearch.crawler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {
    @JsonProperty("Sports")
    private List<Sports> sports;

    public List<Sports> getSports()
    {
        return sports;
    }
    public void setSports(List<Sports> sports)
    {
        this.sports = sports;
    }
}
