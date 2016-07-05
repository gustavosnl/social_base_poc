package com.glima.socialbasepoc.customview.card;

import android.databinding.BaseObservable;

import com.glima.socialbasepoc.model.Game;

/**
 * Created by gustavo on 05/07/16.
 */
public class GameCardViewModel extends BaseObservable {

    private Game game;

    public GameCardViewModel(Game game) {
        this.game = game;
    }


}
