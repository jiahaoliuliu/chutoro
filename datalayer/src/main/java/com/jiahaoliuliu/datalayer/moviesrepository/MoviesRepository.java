package com.jiahaoliuliu.datalayer.moviesrepository;

import com.jiahaoliuliu.domain.IMovie;
import com.jiahaoliuliu.networklayer.ITMDBService;
import com.jiahaoliuliu.networklayer.MoviesList;
import com.jiahaoliuliu.networklayer.NetworkModule;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class MoviesRepository implements IMoviesRepository {

    // TODO: Inject this
    private NetworkModule networkModule;
    private ITMDBService tmdbService;

    public MoviesRepository() {
        networkModule = new NetworkModule();
        tmdbService = networkModule.provideITmdbService();
    }

    @Override
    public Single<List<? extends IMovie>> retrieveMoviesList() {
        // TODO: Check if the network is available. If not, use the cache or memory
        return tmdbService.getMoviesList()
                .map(moviesList -> moviesList.getMoviesList());
    }
}
