package com.jiahaoliuliu.networklayer;

import com.google.gson.annotations.SerializedName;
import com.jiahaoliuliu.domain.IMovie;

public class Movie implements IMovie {

    /**
     * "vote_count": 1733,
     * "id": 335983,
     * "video": false,
     * "vote_average": 6.6,
     * "title": "Venom",
     * "popularity": 376.076,
     * "poster_path": "/2uNW4WbgBXL25BAbXGLnLqX71Sw.jpg",
     * "original_language": "en",
     * "original_title": "Venom",
     * "genre_ids": [878],
     * "backdrop_path": "/VuukZLgaCrho2Ar8Scl9HtV3yD.jpg",
     * "adult": false,
     * "overview": "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego \"Venom\" to save his life.",
     * "release_date": "2018-10-03"
     **/
    private String id;
    private String title;
    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String imageUrl;

    public Movie(String id, String title, String description, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null)
            return false;
        return imageUrl != null ? imageUrl.equals(movie.imageUrl) : movie.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
