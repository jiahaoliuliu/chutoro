package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

public class MoviesListModel implements MoviesListContract.Model {

    public MoviesListModel() {
    }

    @Override
    public Single<List<IMovie>> retrieveMoviesList() {
        return null;
    }
}
