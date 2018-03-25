package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Each row item has:
 *  - Movie name
 *  - Movie popularity
 * Extra features:
 *  - Show the movie genres in the movie row item
 *  - Show at least 1 associated image for each movie row item
 */

@AutoValue
public abstract class ListOfMovies implements Parcelable {

    public abstract int page();

    public abstract int total_results();

    public abstract int total_pages();

    public abstract List<Results> results();

    public static ListOfMovies.Builder builder() {
        return new AutoValue_ListOfMovies.Builder();
    }

    public static TypeAdapter<ListOfMovies> typeAdapter(Gson gson) {
        return new AutoValue_ListOfMovies.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder public abstract static class Builder{

        public abstract Builder page(int page);

        public abstract Builder total_results(int count);

        public abstract Builder total_pages(int total);

        public abstract Builder results(List<Results> results);

        public abstract ListOfMovies build();
    }
}
