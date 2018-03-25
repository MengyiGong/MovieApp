package com.example.maggie.moviesmaggie;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.example.maggie.moviesmaggie.adapter.ViewPagerAdapter;
import com.example.maggie.moviesmaggie.data.DataManager;
import com.example.maggie.moviesmaggie.data.model.AllGenres;
import com.example.maggie.moviesmaggie.fragments.NowPlayingFragment;
import com.example.maggie.moviesmaggie.fragments.UpcomingMoviesFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {
    private long mBackPressedTime;

    @Inject
    DataManager dataManager;

    private String[] tabTitle = {"Now Playing","Upcoming Movies"};
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportActionBar().setTitle(R.string.movie_app_title);
        }

        initView();
    }

    public void initView() {
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(new WeakReference<>(new NowPlayingFragment().newInstance()).get());
        list_fragment.add(new WeakReference<>(new UpcomingMoviesFragment().newInstance()).get());

        viewPager= (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                list_fragment, Arrays.asList(tabTitle));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.language_en:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        long curTime = SystemClock.uptimeMillis();
        if ((curTime - mBackPressedTime) < (3 * 1000)) {
            finish();
        } else {
            mBackPressedTime = curTime;
            showToast(getString(R.string.double_click_exit));
        }
    }


}
