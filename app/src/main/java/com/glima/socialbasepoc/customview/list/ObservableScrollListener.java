package com.glima.socialbasepoc.customview.list;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by gustavo on 06/07/16.
 */
public class ObservableScrollListener extends RecyclerView.OnScrollListener{

    private Boolean isLoading = true;
    private PublishSubject<Boolean> scrollFinishedPublishSubject = PublishSubject.create();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isLoading = true;
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        if (dy > 0) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

            if (isLoading) {
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    scrollFinishedPublishSubject.onNext(true);
                    isLoading = false;
                }
            }
        }
    }

    public Observable<Boolean> getScrollFinishedObservable() {
        return scrollFinishedPublishSubject.asObservable();
    }
}
