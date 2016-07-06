package com.glima.socialbasepoc.customview.card;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.databinding.ViewCardTvShowBinding;

/**
 * Created by gustavo on 05/07/16.
 */
public class HorizontalTvShowCardView extends RelativeLayout {

    ViewDataBinding viewDataBinding;
    TvShowCardViewModel viewModel;


    public HorizontalTvShowCardView(Context context) {
        super(context);
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card_tv_show, this, true);
    }

    public void attachViewModel(TvShowCardViewModel viewModel) {
        this.viewModel = viewModel;
        ((ViewCardTvShowBinding) viewDataBinding).setTvShow(viewModel);
    }

}
