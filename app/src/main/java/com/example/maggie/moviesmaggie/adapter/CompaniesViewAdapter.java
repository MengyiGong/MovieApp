package com.example.maggie.moviesmaggie.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maggie.moviesmaggie.R;
import com.example.maggie.moviesmaggie.data.model.ProductionCompanies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CompaniesViewAdapter extends RecyclerView.Adapter<CompaniesViewAdapter.MyViewHolder> {
    private List<ProductionCompanies> mData;
    private Activity activity;

    public CompaniesViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.company_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ProductionCompanies company = mData.get(position);
        String url = "http://image.tmdb.org/t/p/w92" + company.logo_path();
        Log.e("CompaniesViewAdapter", "logo_path:" + url);
        Glide.with(activity)
                .load(url)
                .placeholder(R.mipmap.placeholder)
                .into(holder.company_pic);
        holder.company_name.setText(company.name());


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<ProductionCompanies> company) {
        mData = company;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.company_pic)
        ImageView company_pic;
        @BindView(R.id.company_name)
        TextView company_name;


        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
