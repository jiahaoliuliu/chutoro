package com.jiahaoliuliu.datalayer.itemsrepository;

import android.util.Log;

import com.jiahaoliuliu.entity.Item;
import com.jiahaoliuliu.networklayer.ItemService;
import com.jiahaoliuliu.storagelayer.ItemImpl;
import com.jiahaoliuliu.storagelayer.ItemsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ItemsRepositoryImpl implements ItemsRepository {

    private static final String TAG = "ItemsRepositoryImpl";

    private final ItemService tmdbService;
    private final ItemsDatabase itemsDatabase;
    // Temporal memory for the items list
    private List<? extends Item> memoryCache = new ArrayList<>();

    public ItemsRepositoryImpl(ItemService tmdbService, ItemsDatabase itemsDatabase) {
        this.tmdbService = tmdbService;
        this.itemsDatabase = itemsDatabase;
    }

    @Override
    public Single<? extends List<? extends Item>> retrieveItemsList() {
        // List of Priorities
        Single<? extends List<? extends Item>> backendSource = retrieveItemsListFromBackend();
        Single<? extends List<? extends Item>> storageSource = retrieveItemsListFromStorage();
        Single<? extends List<? extends Item>> cacheSource = retrieveItemsListFromCache();

        return Single.concat(backendSource, storageSource, cacheSource)
                .filter(source -> !source.isEmpty())
                .first(memoryCache);
    }

    private Single<? extends List<? extends Item>> retrieveItemsListFromBackend() {
        return tmdbService.getItemsList()
             .map(itemsListBackend -> itemsListBackend.getItemsList())
             .doOnSuccess(itemsList -> {
                // Updates the internal cache
                saveItemsListToCache(itemsList);
                // Save the content into the database
                for (Item item: itemsList) {
                    Log.v(TAG, "Trying to save " + item + " into the database");
                    itemsDatabase.itemDao().upsert(new ItemImpl(item));
                }
             }).onErrorResumeNext(throwable -> {
                 Log.e(TAG, "Error retrieving data from backend", throwable);
                 return Single.just(new ArrayList<>());
             });
    }

    private Single<? extends List<? extends Item>> retrieveItemsListFromCache() {
        return Single.just(memoryCache);
    }

    private Single<? extends List<? extends Item>> retrieveItemsListFromStorage() {
        return itemsDatabase.itemDao().getAllItems()
                .doOnSuccess(itemsList -> saveItemsListToCache(itemsList))
                .onErrorResumeNext(throwable -> {
                   Log.e(TAG, "Error retrieving data from the database ", throwable);
                   return Single.just(new ArrayList<>());
                });
    }

    private void saveItemsListToCache(List<? extends Item> newItemsList) {
        this.memoryCache = newItemsList;
    }
}
