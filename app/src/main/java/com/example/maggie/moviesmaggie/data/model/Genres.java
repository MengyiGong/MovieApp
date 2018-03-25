package com.example.maggie.moviesmaggie.data.model;


import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Genres implements Parcelable {

    public abstract int id();

    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Genres.Builder();
    }

    public static TypeAdapter<Genres> typeAdapter(Gson gson) {
        return new AutoValue_Genres.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder  public abstract static class Builder {

        public abstract Builder id(int id);

        public abstract Builder name(String name);

        public abstract Genres build();
    }

}
