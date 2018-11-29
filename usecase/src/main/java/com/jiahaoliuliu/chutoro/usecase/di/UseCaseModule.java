package com.jiahaoliuliu.chutoro.usecase.di;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    RetrieveTransactionsListUseCase provideRetrieveTransactionsListUseCase(
            ITransactionsRepository transactionsRepository) {
        return new RetrieveTransactionsListUseCase(transactionsRepository);
    }
}
