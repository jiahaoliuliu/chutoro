package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

import io.reactivex.Single;

/**
 * Contract for MVP
 */
public interface MoviesListContract {

    interface View {

        /**
         * Show the list of movies on the screen
         * @param moviesList
         */
        void showMoviesList(List<? extends IMovie> moviesList);
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
        void retrieveMoviesList();

        /**
         * Dispose all the retained memory
         */
        void dispose();
    }

    interface Model {
        /**
         * Method used to retrieve a list of movies
         * @return Single of a list of movies
         */
        Single<List<? extends IMovie>> retrieveMoviesList();
    }
}
