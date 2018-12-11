package com.jiahaoliuliu.chutoro.usecase.di;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.usecase.AddTransactionUseCase;
import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;
import com.jiahaoliuliu.chutoro.usecase.UpdateTransactionsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    RetrieveTransactionsListUseCase provideRetrieveTransactionsListUseCase(
            ITransactionsRepository transactionsRepository) {
        return new RetrieveTransactionsListUseCase(transactionsRepository);
    }

    @Provides
    UpdateTransactionsUseCase provideUpdateTransactionsUseCase(
            ITransactionsRepository transactionsRepository) {
        return new UpdateTransactionsUseCase(transactionsRepository);
    }

    @Provides
    AddTransactionUseCase provideAddTransactionUseCase(
            ITransactionsRepository transactionsRepository) {
        return new AddTransactionUseCase(transactionsRepository);
    }
}
