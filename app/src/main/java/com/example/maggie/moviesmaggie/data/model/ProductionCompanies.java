package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class ProductionCompanies implements Parcelable {

    public abstract int id();

    @Nullable public abstract String logo_path();

    public abstract String name();

    @Nullable public abstract String origin_country();

    public static Builder builder() {
        return new AutoValue_ProductionCompanies.Builder();
    }

    public static TypeAdapter<ProductionCompanies> typeAdapter(Gson gson) {
        return new AutoValue_ProductionCompanies.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder public abstract static class Builder {

        public abstract Builder id(int id);

        public abstract Builder logo_path(String logo_path);

        public abstract Builder name(String name);

        public abstract Builder origin_country(String origin_country);

        public abstract ProductionCompanies build();
    }
}
