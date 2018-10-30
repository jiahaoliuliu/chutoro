package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

/**
 * Contract for MVP
 */
public interface MoviesListContract {

    interface View {
    }

    interface Presenter {
        /**
         * Set the current view
         * @param view
         */
        void setView(View view);

        /**
         * Method used to retrieve a list of movies
         * @return Single of a list of movies
         */
        Single<List<IMovie>> retrieveMoviesList();
    }

    interface Model {
        /**
         * Method used to retrieve a list of movies
         * @return Single of a list of movies
         */
        Single<List<IMovie>> retrieveMoviesList();
    }
}
