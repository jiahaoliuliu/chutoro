package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

public class MoviesListPresenter implements MoviesListContract.Presenter {

    private final MoviesListContract.Model model;

    private MoviesListContract.View view;

    public MoviesListPresenter() {
        this.model = new MoviesListModel(this);
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
