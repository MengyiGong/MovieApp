package com.example.maggie.moviesmaggie.dagger.modules;


import android.content.Context;

import com.example.maggie.moviesmaggie.MyApplication;
import com.example.maggie.moviesmaggie.ToastUtil;
import com.example.maggie.moviesmaggie.dagger.scopes.ApplicationContext;
import com.example.maggie.moviesmaggie.data.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.example.maggie.moviesmaggie.network.RetrofitConfig;

@Module
public class ApplicationModule {
    private final MyApplication mApplication;
    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    ToastUtil provideToastUtil(@ApplicationContext Context context) {
        return new ToastUtil(context);
    }

    @Provides
    @Singleton
    MovieService movieService(MyApplication application) {
        return new RetrofitConfig().movieService(application);
    }
}
