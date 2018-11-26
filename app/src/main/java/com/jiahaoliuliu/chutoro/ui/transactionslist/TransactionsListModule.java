package com.jiahaoliuliu.chutoro.ui.transactionslist;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsListModule {

    @Provides
    @Singleton
    TransactionsListContract.Presenter providePresenter(TransactionsListContract.Model model) {
        return new TransactionsListPresenter(model);
    }

    @Provides
    @Singleton
    TransactionsListContract.Model provideModel(ITransactionsRepository ITransactionsRepository) {
        return new TransactionsListModel(ITransactionsRepository);
    }
}
