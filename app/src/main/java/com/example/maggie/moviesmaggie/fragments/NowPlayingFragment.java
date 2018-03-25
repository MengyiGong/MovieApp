package com.example.maggie.moviesmaggie.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maggie.moviesmaggie.MovieDetailActivity;
import com.example.maggie.moviesmaggie.R;
import com.example.maggie.moviesmaggie.adapter.LoadMoreWrapper;
import com.example.maggie.moviesmaggie.adapter.OnItemClickListener;
import com.example.maggie.moviesmaggie.adapter.RecyclerViewAdapter;
import com.example.maggie.moviesmaggie.dagger.component.ActivityComponent;
import com.example.maggie.moviesmaggie.data.model.ListOfMovies;
import com.example.maggie.moviesmaggie.data.model.Results;
import com.example.maggie.moviesmaggie.presenter.NowPlayingMoviesPresenter;
import com.example.maggie.moviesmaggie.presenter.UpcomingMoviesPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class NowPlayingFragment extends LoadingFragment implements NowPlayingMoviesPresenter.View,
        SwipeRefreshLayout.OnRefreshListener, LoadMoreWrapper.OnLoadListener {
    @Inject
    NowPlayingMoviesPresenter playingMoviesPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(this);
    LoadMoreWrapper loadMoreWrapper = new LoadMoreWrapper(mAdapter);
    List<Results> movieData = new ArrayList<Results>();

    private int page;
    private int total_pages;
    private int total_results;
    private boolean isLoad;

    public static NowPlayingFragment newInstance() {
        return new NowPlayingFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playingMoviesPresenter.attachView(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        loadMoreWrapper.setContext(getContext());
        loadMoreWrapper.setOnLoadListener(this);
        recyclerView.setAdapter(loadMoreWrapper);
        Log.e("UpcomingMoviesFragment", "recyclerView calling addOnItemTouchListener");
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Results results = movieData.get(position);
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                Toast.makeText(getActivity(), results.title(), Toast.LENGTH_LONG).show();
                String imageUrl = "http://image.tmdb.org/t/p/w500" + results.backdrop_path();
                bundle.putString("imgUrl", imageUrl);
                bundle.putString("title", results.title());
                bundle.putString("id", results.id());
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * pull to refresh
     */
    @Override
    public void onRefresh() {
        Log.e("UpcomingMoviesFragment", "calling onRefresh()");
        if (!isLoad) {
            Log.e("UpcomingMoviesFragment", "loadMoreWrapper.showLoadMore()");
            loadMoreWrapper.showLoadMore();
            playingMoviesPresenter.loadData(1);
            isLoad = true;
        } else {
            swipeRefresh.setRefreshing(false);
        }
    }

    /**
     * reload
     */
    @Override
    public void onRetry() {
        int num = page;
        playingMoviesPresenter.loadData(num);
        isLoad = true;
    }

    /**
     * load more
     */
    @Override
    public void onLoadMore() {
        int num = page + 1;
        Log.e("UpcomingMoviesFragment", "calling onLoadMore(); current page =" +num + ", total_page = " + total_pages);
        if (num < total_pages) {
            if (!isLoad) {
                playingMoviesPresenter.loadData(num);
                isLoad = true;
            }
        } else {
            Log.e("UpcomingMoviesFragment", "calling showLoadComplete()...");
            loadMoreWrapper.showLoadComplete();
        }
    }

    @Override
    public void onDestroyView() {
        if (isLoad) {
            loadMoreWrapper.showLoadError();
        }
        playingMoviesPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_upcoming_movies;
    }

    @Override
    protected void initData() {
        swipeRefresh.setRefreshing(true);
        playingMoviesPresenter.loadData(1);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        isLoad = false;
    }

    @Override
    public void showMessage(String message) {
        hideProgress();
        loadMoreWrapper.showLoadError();
        Log.e("NowPlayingFragment", message);
        showToast(getString(R.string.load_failed));
    }

    @Override
    public void showMovie(ListOfMovies listOfMovies) {
        page = listOfMovies.page();
        total_pages = listOfMovies.total_pages();

        total_results = listOfMovies.total_results();

        mAdapter.setDate(page, listOfMovies.results());
        if (page == 1) {
            movieData.clear();
            movieData.addAll(listOfMovies.results());
            loadMoreWrapper.notifyDataSetChanged();
        } else {
            movieData.addAll(listOfMovies.results());
            loadMoreWrapper.notifyItemInserted(page);
        }
        hideProgress();
    }

    @Override
    protected void injectDagger(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
