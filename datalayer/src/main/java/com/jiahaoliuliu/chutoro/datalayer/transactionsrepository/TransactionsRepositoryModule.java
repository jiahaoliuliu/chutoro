package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionsRepositoryModule {

    @Provides
    @Singleton
    ITransactionsRepository providesTransactionsRepository(TransactionsDatabase transactionsDatabase) {
        return new TransactionsRepository(transactionsDatabase);
    }
}
