package com.jiahaoliuliu.networklayer;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ITMDBService {

    // TODO: Use the interceptor ofr it
    @GET("discover/movie?sort_by=popularity.desc&api_key=0d60bfb30ada10b14af77a1d9d0f32ff")
    Single<MoviesList> getMoviesList();

}
