package com.dwasearch.model;

import com.dwasearch.crawler.model.Source;
import com.dwasearch.model.players.Country;
import com.dwasearch.model.results.Competition;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataSnapshot {
    @JsonProperty("Source")
    private Source source;
    @JsonProperty("Countries")
    private List<Country> countries;
    @JsonProperty("Competitions")
    private List<Competition> competitions;

    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
    public List<Country> getCountries() {
        return countries;
    }
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
    public List<Competition> getCompetitions() {
        return competitions;
    }
    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
