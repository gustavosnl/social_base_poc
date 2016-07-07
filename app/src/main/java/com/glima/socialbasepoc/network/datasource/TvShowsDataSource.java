package com.glima.socialbasepoc.network.datasource;

import com.glima.socialbasepoc.model.Show;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 05/07/16.
 */
public interface TvShowsDataSource {

    Observable<List<Show>> listPopularShows();

    Observable<List<Show>> getNext();
}
