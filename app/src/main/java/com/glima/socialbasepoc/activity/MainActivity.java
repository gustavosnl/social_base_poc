package com.glima.socialbasepoc.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.adapter.TvShowsAdapter;
import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.databinding.ActivityMainBinding;
import com.glima.socialbasepoc.model.Show;
import com.glima.socialbasepoc.network.datasource.TvShowsDataSourceImpl;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class MainActivity extends BaseActivity implements Observer<List<Show>> {

    private RecyclerView recyclerView;

    private TvShowsDataSourceImpl tvShowsDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {

        tvShowsDataSource = new TvShowsDataSourceImpl(this);

        recyclerView = ((ActivityMainBinding) viewDataBinding).showsList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        recyclerView.setAdapter(new TvShowsAdapter(this));

        tvShowsDataSource.listPopularShows()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("LIST_SHOWS", e.getMessage());
    }

    @Override
    public void onNext(List<Show> shows) {
        ((TvShowsAdapter) recyclerView.getAdapter()).attachViewModel(new TvShowListViewModel(shows));
    }
}
