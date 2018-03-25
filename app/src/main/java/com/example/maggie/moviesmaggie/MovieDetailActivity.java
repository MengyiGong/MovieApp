package com.example.maggie.moviesmaggie;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maggie.moviesmaggie.adapter.CompaniesViewAdapter;
import com.example.maggie.moviesmaggie.dagger.component.ActivityComponent;
import com.example.maggie.moviesmaggie.data.model.Genres;
import com.example.maggie.moviesmaggie.data.model.MovieDetailData;
import com.example.maggie.moviesmaggie.data.model.ProductionCompanies;
import com.example.maggie.moviesmaggie.data.model.SpokenLanguages;
import com.example.maggie.moviesmaggie.presenter.MovieDetailPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MovieDetailActivity extends BaseActivity implements MovieDetailPresenter.View {
    @Inject
    MovieDetailPresenter movieDetailPresenter;
    @BindView(R.id.movie_pic)
    ImageView movie_pic;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.tv_wish)
    TextView tv_wish;
    @BindView(R.id.etv_intro)
    TextView etv_intro;
    @BindView(R.id.rb_start)
    MaterialRatingBar rb_start;
    @BindView(R.id.companies)
    RecyclerView companies;

    private MovieDetailData movieData;
    private int isLoad = 1;
    private MenuItem refreshItem;
    private CompaniesViewAdapter mAdapter;
    private String id;
    private boolean isCollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieDetailPresenter.attachView(this);
        companies = (RecyclerView) findViewById(R.id.companies);
        companies.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        mAdapter = new CompaniesViewAdapter(this);
        companies.setAdapter(mAdapter);
        initData();
    }

    private void initData() {
        Bundle data = getIntent().getBundleExtra("data");
        id = data.getString("id");
        movie_pic = (ImageView) findViewById(R.id.movie_pic);
        Glide.with(this)
                .load(data.getString("imgUrl"))
                .into(movie_pic);
        getSupportActionBar().setTitle(data.getString("title"));
        movieDetailPresenter.loadData(Integer.parseInt(id));
    }

    private void refreshData() {
        String intro = "";
        for (Genres genre : movieData.genres()) {
            intro += genre.name() + "/";
        }
        intro = intro.substring(0, intro.length() - 1);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_rating = (TextView) findViewById(R.id.tv_rating);
        tv_wish = (TextView)findViewById(R.id.tv_wish);
        etv_intro = (TextView)findViewById(R.id.etv_intro);
        rb_start = (MaterialRatingBar) findViewById(R.id.rb_start);
        String originLanguage = "";
        if(movieData.original_language().equals("en")){
            originLanguage = "English";
        }

        String spokenLanguage = "";
        for (SpokenLanguages spoken_language : movieData.spoken_languages()) {
            spokenLanguage += spoken_language.name() + "/";
        }
        if(spokenLanguage.length() > 1) {
            spokenLanguage = spokenLanguage.substring(0, spokenLanguage.length() - 1);
        }
        tv_info.setText("Original Title：" + movieData.original_title() +
                "\nOrigin Language：" + originLanguage +
                "\nSpoken Language：" + spokenLanguage +
                "\nRelease Date：" + movieData.release_date() +
                "\nGenres：" + intro);
        tv_rating.setText(movieData.vote_average());
        float starts = Float.parseFloat(movieData.vote_average());
        rb_start.setNumStars(5);
        rb_start.setRating(starts / 2);
        tv_wish.setText(movieData.vote_count() + " People Voted");
        etv_intro.setText(movieData.overview());

        mAdapter.setData(movieData.production_companies());
    }


    @Override
    protected void onDestroy() {
        movieDetailPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_movie_detail;
    }

    @Override
    public void showProgress() {
        isLoad = 1;
        ActivityCompat.invalidateOptionsMenu(this);
    }

    @Override
    public void hideProgress() {
        isLoad = 0;
        ActivityCompat.invalidateOptionsMenu(this);
    }

    @Override
    public void showMessage(String message) {
        hideProgress();
        Log.e("MovieDetailActivity", message);
        showToast(getString(R.string.load_failed));
    }

    @Override
    public void showMovieDetail(MovieDetailData movieDetailData) {
        movieData = movieDetailData;
        refreshData();
        hideProgress();
    }

    @Override
    protected void injectDagger(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}