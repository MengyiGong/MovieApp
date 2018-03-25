package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class ProductionCountries implements Parcelable {

    public abstract String iso_3166_1();

    public abstract String name();

    public static Builder builder() {
        return new AutoValue_ProductionCountries.Builder();
    }

    public static TypeAdapter<ProductionCountries> typeAdapter(Gson gson) {
        return new AutoValue_ProductionCountries.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder public abstract static class Builder {

        public abstract Builder iso_3166_1(String iso_3166_1);

        public abstract Builder name(String name);

        public abstract ProductionCountries build();
    }
}
