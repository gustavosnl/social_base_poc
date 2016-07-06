package com.glima.socialbasepoc.customview.card;

import android.databinding.BaseObservable;

import com.glima.socialbasepoc.model.Show;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowCardViewModel extends BaseObservable {

    private Show show;

    public TvShowCardViewModel(Show show) {
        this.show = show;
    }


}
