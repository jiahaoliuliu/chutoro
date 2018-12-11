package com.jiahaoliuliu.chutoro.ui.transactionslist;

import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;
import com.jiahaoliuliu.chutoro.usecase.UpdateTransactionsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsListModule {

    @Provides
    @Singleton
    TransactionsListContract.Presenter providePresenter(
            RetrieveTransactionsListUseCase retrieveTransactionsListUseCase,
            UpdateTransactionsUseCase updateTransactionsUseCase) {
        return new TransactionsListPresenter(retrieveTransactionsListUseCase,
                updateTransactionsUseCase);
    }
}
