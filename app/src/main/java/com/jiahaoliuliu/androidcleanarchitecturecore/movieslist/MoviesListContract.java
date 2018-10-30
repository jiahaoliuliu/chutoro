package com.jiahaoliuliu.androidcleanarchitecturecore.movieslist;

import com.jiahaoliuliu.domain.IMovie;

import java.util.List;

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

        List<IMovie> retrieveMoviesList();
    }

    interface Model {
        List<IMovie> retrieveMoviesList();
    }
}
