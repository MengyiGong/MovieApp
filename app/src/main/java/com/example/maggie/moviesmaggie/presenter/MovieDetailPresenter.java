package com.example.maggie.moviesmaggie.presenter;

import com.example.maggie.moviesmaggie.dagger.scopes.PerActivity;
import com.example.maggie.moviesmaggie.data.DataManager;
import com.example.maggie.moviesmaggie.data.model.MovieDetailData;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@PerActivity
public class MovieDetailPresenter extends Presenter<MovieDetailPresenter.View> {

    private View view;
    private final DataManager mDataManager;

    @Inject
    MovieDetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(View view) {
        activityLifecycle.onNext(ActivityEvent.CREATE);
        this.view = view;
    }

    @Override
    public void detachView() {
        activityLifecycle.onNext(ActivityEvent.DESTROY);
        unSubscribe();
        view = null;
    }

    public void loadData(int id) {
        mDataManager.loadMovieDetail(id)
                .compose(this.<MovieDetailData>bindActivityEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieDetailData>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieDetailData movieDetailData) {
                        view.showMovieDetail(movieDetailData);
                    }
                });
    }

    public interface View {
        void showProgress();

        void hideProgress();

        void showMessage(String message);

        void showMovieDetail(MovieDetailData movieDetailData);
    }
}
