package com.glima.socialbasepoc.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.adapter.TvShowsAdapter;
import com.glima.socialbasepoc.customview.card.TvShowViewModel;
import com.glima.socialbasepoc.customview.list.MargingDecoration;
import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.databinding.ActivityMainBinding;
import com.glima.socialbasepoc.model.Show;
import com.glima.socialbasepoc.network.datasource.TvShowsDataSource;
import com.glima.socialbasepoc.network.datasource.TvShowsDataSourceImpl;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity implements Observer<List<Show>> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private TvShowsDataSource tvShowsDataSource;

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMainBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    protected void init() {
        tvShowsDataSource = new TvShowsDataSourceImpl(this);

        progressBar = ((ActivityMainBinding) viewDataBinding).progressBar;
        recyclerView = ((ActivityMainBinding) viewDataBinding).showsList;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.column_span_count), VERTICAL));
        recyclerView.addItemDecoration(new MargingDecoration(16));
        recyclerView.setAdapter(new TvShowsAdapter(this));

        tvShowsDataSource.listPopularShows()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

        ((TvShowsAdapter) recyclerView.getAdapter()).getObservable()
                .subscribe(new Action1<TvShowViewModel>() {
                    @Override
                    public void call(TvShowViewModel viewModel) {
                        System.out.println(viewModel);
                        startActivity(TvShowInfoActivity.newIntent(MainActivity.this, viewModel));
                    }
                });
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onCompleted() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this,getText(R.string.error_loading_shows), Toast.LENGTH_SHORT).show();
        Log.e("LIST_SHOWS", e.getMessage());
    }

    @Override
    public void onNext(List<Show> shows) {
        ((TvShowsAdapter) recyclerView.getAdapter()).attachViewModel(new TvShowListViewModel(shows));
    }
}
