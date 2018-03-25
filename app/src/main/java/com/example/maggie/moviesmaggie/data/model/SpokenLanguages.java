package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SpokenLanguages implements Parcelable {

    public abstract String iso_639_1();

    public abstract String name();

    public static Builder builder() {
        return new AutoValue_SpokenLanguages.Builder();
    }

    public static TypeAdapter<SpokenLanguages> typeAdapter(Gson gson) {
        return new AutoValue_SpokenLanguages.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder iso_639_1(String iso_639_1);

        public abstract Builder name(String name);

        public abstract SpokenLanguages build();
    }
}