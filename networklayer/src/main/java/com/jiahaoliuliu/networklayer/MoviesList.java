package com.jiahaoliuliu.networklayer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList {

    /**
     * "page": 1,
     * "total_results": 384691,
     * "total_pages": 19235,
     * "results": [
     */
    private int page;
    @SerializedName("results")
    private List<Movie> moviesList;

    public MoviesList(int page, List<Movie> moviesList) {
        this.page = page;
        this.moviesList = moviesList;
    }

    public int getPage() {
        return page;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesList that = (MoviesList) o;

        if (page != that.page) return false;
        return moviesList != null ? moviesList.equals(that.moviesList) : that.moviesList == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + (moviesList != null ? moviesList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoviesList{" +
                "page=" + page +
                ", moviesList=" + moviesList +
                '}';
    }
}
