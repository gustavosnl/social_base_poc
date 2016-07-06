package com.glima.socialbasepoc.model;

import java.io.Serializable;

/**
 * Created by gustavo on 05/07/16.
 */
public class Images implements Serializable {

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
