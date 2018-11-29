package com.jiahaoliuliu.chutoro.usecase.di;

import com.jiahaoliuliu.chutoro.usecase.mapsmsusecase.MapSmsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    MapSmsUseCase provideMapSmsUseCase() {
        return new MapSmsUseCase();
    }
}
