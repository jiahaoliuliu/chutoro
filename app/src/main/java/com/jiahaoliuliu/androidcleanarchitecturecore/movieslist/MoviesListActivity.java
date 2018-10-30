package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiahaoliuliu.androidcleanarchitecturecore.MainApplication;
import com.jiahaoliuliu.androidcleanarchitecturecore.R;

import javax.inject.Inject;

public class MoviesListActivity extends AppCompatActivity implements MoviesListContract.View {

    @Inject
    MoviesListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainApplication.getMainComponent().inject(this);

        presenter.setView(this);
        presenter.retrieveMoviesList();
    }

}
