package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsDatabaseModule {

    private ItemsDatabase itemsDatabase;

    @Provides
    @Singleton
    public ItemsDatabase provideItemsDatabase(Context context) {
        if (itemsDatabase == null) {
            itemsDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    ItemsDatabase.class, ItemsDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return itemsDatabase;
    }
}
