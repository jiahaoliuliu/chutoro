package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

public class MoviesListModel implements MoviesListContract.Model {

    private final MoviesListContract.Presenter presenter;

    // TODO: Inject the repository
    public MoviesListModel(MoviesListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Single<List<IMovie>> retrieveMoviesList() {
        return null;
    }
}
