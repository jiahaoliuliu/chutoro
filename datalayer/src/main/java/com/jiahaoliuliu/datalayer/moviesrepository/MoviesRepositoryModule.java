package com.jiahaoliuliu.datalayer.moviesrepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesRepositoryModule {

    @Provides
    @Singleton
    IMoviesRepository providesMoviesRepository() {
        return new MoviesRepository();
    }
}
