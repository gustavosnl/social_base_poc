package com.glima.socialbasepoc.customview.list;

import android.databinding.BaseObservable;

import com.glima.socialbasepoc.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowListViewModel extends BaseObservable {

    List<Show> shows = new ArrayList<>();

    public TvShowListViewModel(List<Show> shows) {
        this.shows.addAll(shows);
    }

    public List<Show> getShows() {
        return shows;
    }
}
