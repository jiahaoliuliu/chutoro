package com.jiahaoliuliu.networklayer;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private Retrofit retrofit;

    public NetworkModule() {
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }

    @Provides
    public ITMDBService provideITmdbService() {
        return retrofit.create(ITMDBService.class);
    }
}
