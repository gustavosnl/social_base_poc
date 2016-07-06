package com.glima.socialbasepoc.network.datasource;

import android.content.Context;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.model.Show;
import com.glima.socialbasepoc.network.parser.ShowParser;
import com.glima.socialbasepoc.network.restclient.TvShowsRestClient;
import com.glima.socialbasepoc.network.service.BaseDataSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by gustavo on 05/07/16.
 */
public class TvShowsDataSourceImpl extends BaseDataSource implements TvShowsDataSource {
    private Integer PAGE = 1;
    private final Integer ITEMS_PER_PAGE = 20;
    private TvShowsRestClient showsClient;

    public TvShowsDataSourceImpl(Context context) {
        super(context, R.string.api_trakt);
        showsClient = retrofit.create(TvShowsRestClient.class);
    }

    @Override
    public Observable<Show> getShowInfo(String showId) {
        return null;
    }

    @Override
    public Observable<List<Show>> listPopularShows() {
        return showsClient.getPopularShows(1, 20).subscribeOn(Schedulers.io()).map(new Func1<Response<String>, List<Show>>() {
            @Override
            public List<Show> call(Response<String> response) {
                try {
                    return new ShowParser().parse(new ByteArrayInputStream(response.body().getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ArrayList<>();
                }
            }
        });
    }

    public Observable<List<Show>> getNext() {
        return getPaged(PAGE++);
    }

    private Observable<List<Show>> getPaged(final Integer page) {
        return showsClient.getPopularShows(page, ITEMS_PER_PAGE)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Response<String>, List<Show>>() {
                    @Override
                    public List<Show> call(Response<String> response) {
                        try {
                            return new ShowParser().parse(new ByteArrayInputStream(response.body().getBytes()));
                        } catch (IOException e) {
                            return new ArrayList<>();
                        }
                    }
                });
    }

}
