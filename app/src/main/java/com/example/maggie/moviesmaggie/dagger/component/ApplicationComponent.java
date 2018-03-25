package com.example.maggie.moviesmaggie.dagger.component;

import android.content.Context;

import com.example.maggie.moviesmaggie.MyApplication;
import com.example.maggie.moviesmaggie.ToastUtil;
import com.example.maggie.moviesmaggie.dagger.modules.ApplicationModule;
import com.example.maggie.moviesmaggie.dagger.scopes.ApplicationContext;
import com.example.maggie.moviesmaggie.data.DataManager;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication application);

    @ApplicationContext
    Context context();

    MyApplication mApplication();

    ToastUtil toastUtil();

    DataManager dataManager();

//    RxBus rxBus();
}
