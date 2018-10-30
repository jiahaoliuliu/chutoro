package com.jiahaoliuliu.datalayer.moviesrepository;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

/**
 * A simple repository for the movies
 */
public interface IMoviesRepository {

    /**
     * Retrieve a list of movies either from network, either from the
     * temporal memory, either from the database
     * @return a list of movies
     */
    Single<List<? extends IMovie>> retrieveMoviesList();
}
