package com.jiahaoliuliu.datalayer.itemsrepository;

import com.jiahaoliuliu.networklayer.ItemService;
import com.jiahaoliuliu.storagelayer.ItemsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsRepositoryModule {

    @Provides
    @Singleton
    ItemsRepository providesItemsRepository(ItemService tmdbService, ItemsDatabase itemsDatabase) {
        return new ItemsRepositoryImpl(tmdbService, itemsDatabase);
    }
}
