package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jiahaoliuliu.androidcleanarchitecturecore.MainApplication;
import com.jiahaoliuliu.androidcleanarchitecturecore.R;
import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import javax.inject.Inject;

public class MoviesListActivity extends AppCompatActivity implements MoviesListContract.View {

    private static final String TAG = "MoviesListActivity";

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

    @Override
    public void showMoviesList(List<? extends IMovie> moviesList) {
        // TODO: Implement this
        Log.v(TAG, "List of movies retrieved " + moviesList.toString());
    }

    @Override
    protected void onDestroy() {
        presenter.dispose();
        super.onDestroy();
    }
}
