package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

public class MoviesListPresenter implements MoviesListContract.Presenter {

    private final MoviesListContract.Model model;

    private MoviesListContract.View view;

    public MoviesListPresenter() {
        this.model = new MoviesListModel();
    }

    @Override
    public void setView(MoviesListContract.View view) {
        this.view = view;
    }

    @Override
    public List<IMovie> retrieveMoviesList() {
        return model.retrieveMoviesList();
    }
}
