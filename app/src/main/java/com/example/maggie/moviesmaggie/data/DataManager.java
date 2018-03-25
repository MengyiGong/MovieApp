package com.example.maggie.moviesmaggie.data;

import android.util.Log;

import com.example.maggie.moviesmaggie.data.model.AllGenres;
import com.example.maggie.moviesmaggie.data.model.ListOfMovies;
import com.example.maggie.moviesmaggie.data.model.MovieDetailData;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


@Singleton
public class DataManager {
    private final MovieService mMovieService;

    @Inject
    DataManager(MovieService movieService) {
        mMovieService = movieService;
    }

    public Observable<ListOfMovies> loadNowPlayingMovies(int page) {
        Log.e("DataManager", "loadNowPlayingMovies page = " + page);
        return mMovieService.getNowPlayingMovie(page);
    }

    public Observable<ListOfMovies> loadUpcomingMovies(int page) {
        Log.e("DataManager", "loadUpcomingMovies page = " + page);
        return mMovieService.getUpcomingMovie(page);
    }

    public Observable<MovieDetailData> loadMovieDetail(int id) {
        return mMovieService.getMovieDetailData(id);
    }

    public Observable<AllGenres> getMovieGenres() {
        return mMovieService.getMovieGenres();
    }
}
