package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

import com.jiahaoliuliu.chutoro.networklayer.ItemService;
import com.jiahaoliuliu.chutoro.storagelayer.ItemsDatabase;

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
