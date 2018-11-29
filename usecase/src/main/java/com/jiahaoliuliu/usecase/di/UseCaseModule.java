package com.jiahaoliuliu.usecase.di;

import com.jiahaoliuliu.usecase.MapSmsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    MapSmsUseCase provideMapSmsUseCase() {
        return new MapSmsUseCase();
    }
}
