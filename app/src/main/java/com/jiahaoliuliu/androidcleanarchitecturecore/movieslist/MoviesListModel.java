package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

public class MoviesListModel implements MoviesListContract.Model {

    // TODO: Inject the repository
    public MoviesListModel() {
    }

    @Override
    public List<IMovie> retrieveMoviesList() {
        return null;
    }
}
