package com.glima.socialbasepoc.network.datasource;

import android.content.Context;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.model.Game;
import com.glima.socialbasepoc.network.restclient.GiantBombService;
import com.glima.socialbasepoc.network.service.BaseDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 05/07/16.
 */
public class GamesDataSourceImpl extends BaseDataSource implements GamesDataSource {
    private GiantBombService service;

    public GamesDataSourceImpl(Context context) {
        super(context.getString(R.string.api_giant_bomb));
        service = retrofit.create(GiantBombService.class);
    }


    @Override
    public Observable<Game> getGameById(String gameId) {
        return null;
    }

    @Override
    public Observable<List<Game>> listGames() {
        return null;
    }
}
