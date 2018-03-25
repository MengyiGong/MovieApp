package com.example.maggie.moviesmaggie.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maggie.moviesmaggie.R;
import com.example.maggie.moviesmaggie.data.model.AllGenres;
import com.example.maggie.moviesmaggie.data.model.Genres;
import com.example.maggie.moviesmaggie.data.model.MovieDetailData;
import com.example.maggie.moviesmaggie.data.model.Results;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Results> mData = new ArrayList<Results>();
    private List<Genres> mGenres = new ArrayList<Genres>();
    private Fragment fragment;

    public RecyclerViewAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Results results = mData.get(position);
       // final Genres genres = mGenres.get(position);

        Log.e("RecyclerViewAdapter", "see size " + mGenres.size());
        String genre = "";
//        for (int g : results.genre_ids()) {
//            genre += g + "/";
//        }
        String imageUrl = "http://image.tmdb.org/t/p/w185";
        Glide.with(fragment)
                .load(imageUrl + results.poster_path())
                .placeholder(R.mipmap.placeholder)
                .into(holder.iv_avatar);
        holder.tv_title.setText(results.title());
        holder.tv_rating.setText("Popularity: " + results.popularity().substring(0, 3));
        holder.tv_cast.setText(genre);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setDate(int position, List<Results> results) {
        if (position == 1) {
            mData.clear();
        }
        mData.addAll(results);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.iv_avatar)
        ImageView iv_avatar;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_rating)
        TextView tv_rating;
        @BindView(R.id.tv_cast)
        TextView tv_cast;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
