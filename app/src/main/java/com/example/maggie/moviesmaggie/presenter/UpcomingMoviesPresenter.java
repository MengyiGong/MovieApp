package com.example.maggie.moviesmaggie.presenter;

import android.util.Log;

import com.example.maggie.moviesmaggie.dagger.scopes.PerActivity;
import com.example.maggie.moviesmaggie.data.DataManager;
import com.example.maggie.moviesmaggie.data.model.AllGenres;
import com.example.maggie.moviesmaggie.data.model.ListOfMovies;


import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@PerActivity
public class UpcomingMoviesPresenter extends Presenter<UpcomingMoviesPresenter.View> {
    private View view;
    private final DataManager mDataManager;

    @Inject
    UpcomingMoviesPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(View view) {
        fragmentLifecycle.onNext(FragmentEvent.CREATE_VIEW);
        this.view = view;
    }

    @Override
    public void detachView() {
        fragmentLifecycle.onNext(FragmentEvent.DESTROY_VIEW);
        view = null;
    }

    public void loadData(int page) {
       // mDataManager.getMovieGenres();
        Log.e("UpcomingMoviesPresenter", "Start loading data... page = " + page);
        mDataManager.loadUpcomingMovies(page)
                .compose(this.<ListOfMovies>bindFragmentEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListOfMovies>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable exception) {
                        exception.printStackTrace();
                        Log.e("UpcomingMoviesPresenter", "onError:" + ((Exception)exception).getStackTrace());
                        Log.e("UpcomingMoviesPresenter", "onError:" + exception.toString());
                        Log.e("UpcomingMoviesPresenter", "onError.getMessage:" + exception.getMessage());

                        view.showMessage(exception.getMessage());
                    }

                    @Override
                    public void onNext(ListOfMovies listOfMovies) {
                        view.showMovie(listOfMovies);
                    }
                });
    }


    public interface View {

        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void showMovie(ListOfMovies listOfMovies);
    }

}
