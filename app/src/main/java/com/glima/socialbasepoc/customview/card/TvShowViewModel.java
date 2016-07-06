package com.glima.socialbasepoc.customview.card;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.glima.socialbasepoc.model.Show;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by gustavo on 06/07/16.
 */
public class TvShowViewModel extends BaseObservable implements Serializable {

    private Show show;

    public TvShowViewModel(Show show) {
        this.show = show;
    }

    @Bindable
    public String getTitle() {
        return show.getTitle();
    }

    public String getThumbnailUrl() {
        return show.getImages().getThumbnail();
    }

    public String getLogoUrl() {
        return show.getImages().getLogo();
    }

    @Bindable
    public String getDescription() {
        return show.getDescription();
    }

    @Bindable
    public String getLaunchYear() {
        return show.getLaunchYear();
    }

    @Bindable
    public String getTotalEpisodes() {
        return show.getTotalEpisodes().toString();
    }


    @BindingAdapter({"bind:thumbnailUrl"})
    public static void loadThumbnailImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter({"bind:logoUrl"})
    public static void loadLogoImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

}
