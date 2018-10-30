package com.jiahaoliuliu.androidcleanarchitecturecore;

import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListActivity;
import com.jiahaoliuliu.androidcleanarchitecturecore.movieslist.MoviesListModule;

import dagger.Component;

@Component(modules = {MoviesListModule.class})
public interface MainComponent {

    void inject(MoviesListActivity moviesListActivity);
}
