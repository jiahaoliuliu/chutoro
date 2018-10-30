package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesListModule {

    @Provides
    MoviesListContract.Presenter providePresenter() {
        return new MoviesListPresenter();
    }

    @Provides
    MoviesListContract.Model provideModel() {
        return new MoviesListModel();
    }
}
