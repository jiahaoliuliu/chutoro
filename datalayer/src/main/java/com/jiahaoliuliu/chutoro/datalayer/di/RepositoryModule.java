package com.jiahaoliuliu.chutoro.datalayer.di;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.TransactionsRepository;
import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ITransactionsRepository providesTransactionsRepository(
            TransactionsDatabase transactionsDatabase, CommonTransactionsProvider
            commonTransactionsProvider) {
        return new TransactionsRepository(transactionsDatabase, commonTransactionsProvider);
    }
}
