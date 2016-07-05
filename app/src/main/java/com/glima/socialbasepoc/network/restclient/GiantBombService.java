package com.glima.socialbasepoc.network.restclient;

import com.glima.socialbasepoc.model.Game;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gustavo on 04/07/16.
 */
public interface GiantBombService {

    @GET("games")
    Observable<List<Game>> getGames(@Query("limit") Integer limit, @Query("offset") Integer offset,
                                    @Query("api_key") String apiKey);


}
