package com.example.maggie.moviesmaggie.data;


import com.example.maggie.moviesmaggie.data.model.AllGenres;
import com.example.maggie.moviesmaggie.data.model.ListOfMovies;
import com.example.maggie.moviesmaggie.data.model.MovieDetailData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieService {

    String BaseUrl = "https://api.themoviedb.org/3/";
    String APIKey = "?api_key=f86bc5e2ed5cba6a5525fbc867b7334f";
    String language = "en-US";

    /**
     * Now Playing Movies
     */
    String nowPlaying = "movie/now_playing" + APIKey;
    @GET(nowPlaying)
    Observable<ListOfMovies> getNowPlayingMovie(@Query("page") int page);

    /**
     * upcoming movie
     */
    String upcomingMovies = "movie/upcoming" + APIKey;
    @GET(upcomingMovies)
    Observable<ListOfMovies> getUpcomingMovie(@Query("page") int page);

    /**
     * get movie detail
     */
    String getMovieDetail = "movie/{id}"+ APIKey;
    @GET(getMovieDetail)
    Observable<MovieDetailData> getMovieDetailData(@Path("id") int id);

    /**
     * get movie genre ID
     */
    String getGenres = "genre/movie/list" + APIKey;
    @GET(getGenres)
    Observable<AllGenres> getMovieGenres();

}
