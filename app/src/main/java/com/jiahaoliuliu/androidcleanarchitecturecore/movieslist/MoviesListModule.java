package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesListModule {

    @Provides
    @Singleton
    MoviesListContract.Presenter providePresenter() {
        return new MoviesListPresenter();
    }

    @Provides
    @Singleton
    MoviesListContract.Model provideModel() {
        return new MoviesListModel();
    }
}
