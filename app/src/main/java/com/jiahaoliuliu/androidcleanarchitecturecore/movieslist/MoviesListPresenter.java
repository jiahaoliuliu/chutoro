package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import android.util.Log;

import com.jiahaoliuliu.androidcleanarchitecturecore.MainApplication;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesListPresenter implements MoviesListContract.Presenter {

    private static final String TAG = "MoviesListPresenter";

    @Inject
    MoviesListContract.Model model;

    private MoviesListContract.View view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MoviesListPresenter() {
        MainApplication.getMainComponent().inject(this);
    }

    @Override
    public void setView(MoviesListContract.View view) {
        this.view = view;
    }

    @Override
    public void retrieveMoviesList() {
        compositeDisposable.add(model.retrieveMoviesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieslist -> view.showMoviesList(movieslist),
                        throwable -> {
                            // TODO: Show the error on the screen
                            Log.e(TAG, "Error getting the list of the movies from backend ", throwable);
                        }));
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
