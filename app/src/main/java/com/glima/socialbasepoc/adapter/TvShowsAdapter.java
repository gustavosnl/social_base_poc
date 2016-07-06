package com.glima.socialbasepoc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowsAdapter extends RecyclerView.Adapter {

    List<Show> shows = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public void attachViewModel(TvShowListViewModel viewModel){
        shows.addAll(viewModel.getShows());
        notifyDataSetChanged();
    }
}
