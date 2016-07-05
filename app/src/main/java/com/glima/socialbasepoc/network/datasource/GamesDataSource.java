package com.glima.socialbasepoc.network.datasource;

import com.glima.socialbasepoc.model.Game;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 05/07/16.
 */
public interface GamesDataSource {

    Observable<Game> getGameById(String gameId);

    Observable<List<Game>> listGames();
}
