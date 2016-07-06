package com.glima.socialbasepoc.network.restclient;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gustavo on 04/07/16.
 */
public interface TvShowsRestClient {

    @GET("shows/popular?extended=full,images")
    Observable<Response<String>> getPopularShows
            (@Query("page") Integer page,
             @Query("limit") Integer limit);

}
