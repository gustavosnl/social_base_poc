package com.glima.socialbasepoc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.glima.socialbasepoc.customview.card.HorizontalTvShowCardView;
import com.glima.socialbasepoc.customview.card.TvShowCardViewModel;
import com.glima.socialbasepoc.customview.list.TvShowListViewModel;
import com.glima.socialbasepoc.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.ShowItemViewHolder> {

    private List<Show> shows = new ArrayList<>();
    private Context context;

    public TvShowsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ShowItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowItemViewHolder(new HorizontalTvShowCardView(context));
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

    public class ShowItemViewHolder extends RecyclerView.ViewHolder {
        HorizontalTvShowCardView cardView;

        public ShowItemViewHolder(View itemView) {
            super(itemView);
            cardView = ((HorizontalTvShowCardView) itemView);
        }

        public void attachTVShow(Show show){
            cardView.attachViewModel(new TvShowCardViewModel(show));
        }
    }
}
