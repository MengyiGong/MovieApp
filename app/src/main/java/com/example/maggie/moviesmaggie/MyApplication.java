package com.example.maggie.moviesmaggie;

import android.app.Application;
import android.content.IntentFilter;


import com.example.maggie.moviesmaggie.dagger.component.ApplicationComponent;
import com.example.maggie.moviesmaggie.dagger.component.DaggerApplicationComponent;
import com.example.maggie.moviesmaggie.dagger.modules.ApplicationModule;
import com.example.maggie.moviesmaggie.network.NetworkConnectChangedReceiver;


public class MyApplication extends Application {
    ApplicationComponent mAppComponent;

    private static MyApplication application;
    private boolean isConnected;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initReceiver();
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkConnectChangedReceiver changedReceiver = new NetworkConnectChangedReceiver(this);
        registerReceiver(changedReceiver, filter);
    }

    public ApplicationComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerApplicationComponent.builder().
                    applicationModule(new ApplicationModule(this)).build();
        }
        return mAppComponent;
    }
    public static MyApplication getInstance() {
        return application;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
