package com.glima.socialbasepoc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 05/07/16.
 */
public class Show {

    private String id;
    private String title;
    private String launchYear;
    private String description;
    private Integer totalEpisodes;
    private List<String> genres = new ArrayList<>();
    private Images images;

    public Show(String id, String title, String launchYear, String description, Integer totalEpisodes, List<String> genres, Images images) {
        this.id = id;
        this.title = title;
        this.launchYear = launchYear;
        this.description = description;
        this.totalEpisodes = totalEpisodes;
        this.genres.addAll(genres);
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public String getDescription() {
        return description;
    }

    public Integer getTotalEpisodes() {
        return totalEpisodes;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Images getImages() {
        return images;
    }
}
