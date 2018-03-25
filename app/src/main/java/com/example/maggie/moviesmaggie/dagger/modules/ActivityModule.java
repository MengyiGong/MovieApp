package com.example.maggie.moviesmaggie.dagger.modules;

import android.app.Activity;
import android.content.Context;


import com.example.maggie.moviesmaggie.dagger.scopes.ActivityContext;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
}
