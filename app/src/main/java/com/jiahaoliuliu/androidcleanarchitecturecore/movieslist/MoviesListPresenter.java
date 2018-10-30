package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.androidcleanarchitecturecore.MainApplication;
import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class MoviesListPresenter implements MoviesListContract.Presenter {

    @Inject
    MoviesListContract.Model model;

    private MoviesListContract.View view;

    public MoviesListPresenter() {
        MainApplication.getMainComponent().inject(this);
    }

    @Override
    public void setView(MoviesListContract.View view) {
        this.view = view;
    }

    @Override
    public Single<List<IMovie>> retrieveMoviesList() {
        return model.retrieveMoviesList();
    }
}
