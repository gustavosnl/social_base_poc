package com.glima.socialbasepoc.model;

/**
 * Created by gustavo on 05/07/16.
 */
public class Images {

    private String thumbnail;
    private String logo;


    public Images(String thumbnail, String logo) {
        this.thumbnail = thumbnail;
        this.logo = logo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLogo() {
        return logo;
    }
}
