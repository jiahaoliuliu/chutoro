package com.jiahaoliuliu.androidcleanarchitecturecore;

import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListActivity;
import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListModel;
import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListModule;
import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListPresenter;
import com.jiahaoliuliu.datalayer.moviesrepository.MoviesRepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {MoviesListModule.class, MoviesRepositoryModule.class})
@Singleton
public interface MainComponent {

    void inject(MoviesListActivity moviesListActivity);

    void inject(MoviesListPresenter moviesListPresenter);

    void inject(MoviesListModel moviesListModel);
}
