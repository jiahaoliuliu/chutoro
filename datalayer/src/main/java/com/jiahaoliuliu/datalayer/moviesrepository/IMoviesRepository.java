package com.jiahaoliuliu.datalayer.moviesrepository;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

/**
 * A simple repository for the movies
 */
public interface IMoviesRepository {

    Single<List<IMovie>> retrieveMoviesList();
}
