package com.example.maggie.moviesmaggie.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal;
    private boolean isLoading = true;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = lm.getItemCount();
        int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false;
                previousTotal = totalItemCount;
            } else if (totalItemCount < previousTotal) {
                previousTotal = totalItemCount;
                isLoading = false;
            } else {
                isLoading = true;
            }
        }
        if (!isLoading && visibleItemCount > 0 && totalItemCount - 1 == lastVisibleItemPosition && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
            isLoading = true;
            loadMore();
        }
    }

    public abstract void loadMore();
}
