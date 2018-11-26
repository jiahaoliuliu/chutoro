package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

//import com.jiahaoliuliu.chutoro.networklayer.TransactionService;
import com.jiahaoliuliu.chutoro.storagelayer.ItemsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsRepositoryModule {

    @Provides
    @Singleton
    ItemsRepository providesItemsRepository(
//            TransactionService itemService,
            ItemsDatabase itemsDatabase) {
        return new ItemsRepositoryImpl(
//                itemService,
                itemsDatabase);
    }
}
