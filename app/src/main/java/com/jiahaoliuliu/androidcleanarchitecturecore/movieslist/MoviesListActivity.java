package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiahaoliuliu.androidcleanarchitecturecore.R;

public class MoviesListActivity extends AppCompatActivity implements MoviesListContract.View {

    // TODO: Inject it
    // TODO: Check if link it with lifecycle
    private MoviesListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MoviesListPresenter();
        presenter.setView(this);
        presenter.retrieveMoviesList();
    }

}
