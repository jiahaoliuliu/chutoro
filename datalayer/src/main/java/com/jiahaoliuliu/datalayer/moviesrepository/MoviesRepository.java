package com.jiahaoliuliu.datalayer.moviesrepository;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

public class MoviesRepository implements IMoviesRepository {

    @Override
    public Single<List<IMovie>> retrieveMoviesList() {
        // TODO: Implement this
        // Check if the network is available. If not, use the cache or memory
        return null;
    }
}
