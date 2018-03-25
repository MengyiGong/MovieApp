package com.example.maggie.moviesmaggie.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class MovieDetailData implements Parcelable {

    public abstract String vote_count();

    public abstract String id();

    public abstract String vote_average();

    public abstract String title();

    public abstract String popularity();

    @Nullable public abstract String poster_path();

    public abstract String original_language();

    public abstract String original_title();

    public abstract String tagline();

    public abstract List<Genres> genres();

    public abstract List<ProductionCompanies> production_companies();

    public abstract List<SpokenLanguages> spoken_languages();

    public abstract List<ProductionCountries> production_countries();

    @Nullable public abstract String backdrop_path();

    public abstract String overview();

    public abstract String release_date();

    public static TypeAdapter<MovieDetailData> typeAdapter(Gson gson) {
        return new AutoValue_MovieDetailData.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder public abstract static class Builder {

        public abstract Builder vote_count(String vote_count);

        public abstract Builder id(String id);

        public abstract Builder vote_average(String vote_average);

        public abstract Builder title(String title);

        public abstract Builder popularity(String popularity);

        public abstract Builder poster_path(String poster_path);

        public abstract Builder original_language(String original_language);

        public abstract Builder original_title(String original_title);

        public abstract Builder tagline(String tagline);

        public abstract Builder genres(List<Genres> genres);

        public abstract Builder production_companies(List<ProductionCompanies> production_companies);

        public abstract Builder spoken_languages(List<SpokenLanguages> spoken_languages);

        public abstract Builder production_countries(List<ProductionCountries> production_countries);

        public abstract Builder backdrop_path(String backdrop_path);

        public abstract Builder overview(String overview);

        public abstract Builder release_date(String release_date);

        public abstract MovieDetailData build();
    }
}
