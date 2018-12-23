package com.jiahaoliuliu.chutoro.datalayer.di;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.TransactionsRepository;
import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.storagelayer.MainDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ITransactionsRepository providesTransactionsRepository(
            MainDatabase mainDatabase, CommonTransactionsProvider
            commonTransactionsProvider) {
        return new TransactionsRepository(mainDatabase, commonTransactionsProvider);
    }
}
