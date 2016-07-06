package com.glima.socialbasepoc.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.glima.socialbasepoc.R;
import com.glima.socialbasepoc.customview.card.TvShowViewModel;
import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.databinding.ViewCardTvShowBinding;
import com.glima.socialbasepoc.model.Show;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.ShowItemViewHolder> {

    private List<Show> shows = new ArrayList<>();
    private Context context;
    private PublishSubject<TvShowViewModel> publishSubject = PublishSubject.create();

    public TvShowsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ShowItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_card_tv_show, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowItemViewHolder holder, int position) {
        holder.attachTVShow(shows.get(position));
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void attachViewModel(TvShowListViewModel viewModel) {
        shows.addAll(viewModel.getShows());
        notifyDataSetChanged();
    }

    public Observable<TvShowViewModel> getObservable() {
        return publishSubject.asObservable();
    }

    public class ShowItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ViewDataBinding viewDataBinding;
        TvShowViewModel viewModel;

        public ShowItemViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
            itemView.setOnClickListener(this);
        }

        public void attachTVShow(Show show) {
            viewModel = new TvShowViewModel(show);
            ((ViewCardTvShowBinding) viewDataBinding).setTvShow(viewModel);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, viewModel.getTitle(), Toast.LENGTH_SHORT).show();
            publishSubject.onNext(viewModel);
        }
    }
}
