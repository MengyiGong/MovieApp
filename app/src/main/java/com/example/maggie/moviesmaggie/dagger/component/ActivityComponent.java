package com.example.maggie.moviesmaggie.dagger.component;


import com.example.maggie.moviesmaggie.BaseActivity;
import com.example.maggie.moviesmaggie.MainActivity;
import com.example.maggie.moviesmaggie.MovieDetailActivity;
import com.example.maggie.moviesmaggie.dagger.modules.ActivityModule;
import com.example.maggie.moviesmaggie.dagger.scopes.PerActivity;
import com.example.maggie.moviesmaggie.fragments.LoadingFragment;
import com.example.maggie.moviesmaggie.fragments.NowPlayingFragment;
import com.example.maggie.moviesmaggie.fragments.UpcomingMoviesFragment;


import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(UpcomingMoviesFragment upcomingMoviesFragment);

    void inject(NowPlayingFragment nowPlayingFragment);

    void inject(LoadingFragment loadingFragment);

    void inject(MovieDetailActivity movieDetailActivity);
}

