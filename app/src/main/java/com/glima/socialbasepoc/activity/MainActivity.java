package com.glima.socialbasepoc.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.adapter.TvShowsAdapter;
import com.glima.socialbasepoc.customview.card.TvShowViewModel;
import com.glima.socialbasepoc.customview.list.MargingDecoration;
import com.glima.socialbasepoc.customview.list.ObservableScrollListener;
import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.databinding.ActivityMainBinding;
import com.glima.socialbasepoc.model.Show;
import com.glima.socialbasepoc.network.datasource.TvShowsDataSource;
import com.glima.socialbasepoc.network.datasource.TvShowsDataSourceImpl;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity implements Observer<List<Show>> {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ObservableScrollListener scrollListener;

    private TvShowsDataSource tvShowsDataSource;

    @Override
    protected void init() {
        tvShowsDataSource = new TvShowsDataSourceImpl(this);

        scrollListener = new ObservableScrollListener();
        progressBar = ((ActivityMainBinding) viewDataBinding).progressBar;
        recyclerView = ((ActivityMainBinding) viewDataBinding).showsList;
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.column_span_count)));
        recyclerView.addItemDecoration(new MargingDecoration(16));
        recyclerView.setAdapter(new TvShowsAdapter(this));

        recyclerView.addOnScrollListener(scrollListener);

        setupScrollListener();

        tvShowsDataSource.listPopularShows()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);

        ((TvShowsAdapter) recyclerView.getAdapter()).getObservable()
                .subscribe(new Action1<TvShowViewModel>() {
                    @Override
                    public void call(TvShowViewModel viewModel) {
                        startActivity(TvShowInfoActivity.newIntent(MainActivity.this, viewModel));
                    }
                });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupToolbar() {
        Toolbar toolbar = ((ActivityMainBinding) viewDataBinding).includeToolbar.toolbar;
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCompleted() {
        progressBar.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(this, getText(R.string.error_loading_shows), Toast.LENGTH_SHORT).show();
        Log.e("LIST_SHOWS", e.getMessage());
    }

    @Override
    public void onNext(List<Show> shows) {
        ((TvShowsAdapter) recyclerView.getAdapter()).attachViewModel(new TvShowListViewModel(shows));
    }

    private void setupScrollListener() {
        scrollListener.getScrollFinishedObservable()
                .flatMap(new Func1<Boolean, Observable<List<Show>>>() {
                    @Override
                    public Observable<List<Show>> call(Boolean scrollFinished) {
                        if (scrollFinished) {
                            return tvShowsDataSource
                                    .getNext()
                                    .subscribeOn(Schedulers.io());
                        }
                        return Observable.empty();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }
}
