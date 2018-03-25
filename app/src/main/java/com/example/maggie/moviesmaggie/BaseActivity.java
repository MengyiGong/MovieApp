package com.example.maggie.moviesmaggie;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.maggie.moviesmaggie.dagger.component.ActivityComponent;
import com.example.maggie.moviesmaggie.dagger.component.DaggerActivityComponent;
import com.example.maggie.moviesmaggie.dagger.modules.ActivityModule;


import javax.inject.Inject;

import butterknife.BindView;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    ToastUtil toastUtil;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ActivityComponent mActivityComponent;
    public SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(0xFFFFFFFF);
        }

        injectDagger(activityComponent());
    }
    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(((MyApplication) getApplication()).getAppComponent())
                    .build();
        }
        return mActivityComponent;
    }

    protected void injectDagger(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    protected void showToast(String text) {
        toastUtil.showToast(text);
    }
    protected abstract int getContentViewID();
}
