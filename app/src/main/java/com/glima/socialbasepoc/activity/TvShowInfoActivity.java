package com.glima.socialbasepoc.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.customview.card.TvShowViewModel;
import com.glima.socialbasepoc.databinding.ActivityShowInfoBinding;

public class TvShowInfoActivity extends BaseActivity {
    private static final String BUNDLE_KEY_SHOW = "tv_show";

    private TvShowViewModel viewModel;

    public static Intent newIntent(Context context, TvShowViewModel show) {

        Intent intent = new Intent(context, TvShowInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY_SHOW, show);
        intent.putExtras(bundle);

        return intent;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        viewModel = (TvShowViewModel) getIntent().getSerializableExtra(BUNDLE_KEY_SHOW);
        ((ActivityShowInfoBinding) viewDataBinding).setTvShow(viewModel);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityShowInfoBinding) viewDataBinding).collapsingToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_show_info;
    }
}
