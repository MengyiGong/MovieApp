package com.example.maggie.moviesmaggie.data.model;


import android.os.Parcelable;
import android.support.annotation.Nullable;

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
public abstract class Results implements Parcelable {

    public abstract String vote_count();

    public abstract String id();

    public abstract Float vote_average();

    public abstract String title();

    public abstract String popularity();


    @Nullable public abstract String poster_path();

    public abstract String original_title();

    public abstract List<Integer> genre_ids();

    @Nullable public abstract String backdrop_path();

    public abstract String overview();

    public abstract String release_date();

    public static Results.Builder builder() {
        return new AutoValue_Results.Builder();
    }

    public static TypeAdapter<Results> typeAdapter(Gson gson) {
        return new AutoValue_Results.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder  public abstract static class Builder {

        public abstract Builder vote_count(String vote_count);

        public abstract Builder id(String id);

        public abstract Builder vote_average(Float vote_average);

        public abstract Builder title(String title);

        public abstract Builder popularity(String popularity);

        public abstract Builder poster_path(String poster_path);

        public abstract Builder original_title(String original_title);

        public abstract Builder genre_ids(List<Integer> genre_ids);

        public abstract Builder backdrop_path(String backdrop_path);

        public abstract Builder overview(String overview);

        public abstract Builder release_date(String release_date);

        public abstract Results build();
    }
}
