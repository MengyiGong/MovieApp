package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class AllGenres implements Parcelable {

    public abstract List<Genres> all_genres();

    public static AllGenres.Builder builder() {
        return new AutoValue_AllGenres.Builder();
    }

    public static TypeAdapter<AllGenres> typeAdapter(Gson gson) {
        return new AutoValue_AllGenres.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder  public abstract static class Builder {

        public abstract Builder all_genres(List<Genres> all_genres);

        public abstract AllGenres build();
    }

}
