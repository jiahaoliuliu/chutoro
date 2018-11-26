package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

//import com.jiahaoliuliu.chutoro.networklayer.TransactionService;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsRepositoryModule {

    @Provides
    @Singleton
    ItemsRepository providesItemsRepository(
//            TransactionService itemService,
            TransactionsDatabase transactionsDatabase) {
        return new ItemsRepositoryImpl(
//                itemService,
                transactionsDatabase);
    }
}
