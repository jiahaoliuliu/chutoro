package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransactions;
//import com.jiahaoliuliu.chutoro.networklayer.ItemService;
import com.jiahaoliuliu.chutoro.storagelayer.ItemsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ItemsRepositoryImpl implements ItemsRepository {

    private static final String TAG = "ItemsRepositoryImpl";

//    private final ItemService itemService;
    private final ItemsDatabase itemsDatabase;
    // Temporal memory for the items list
    private List<? extends ITransactions> memoryCache = new ArrayList<>();

    public ItemsRepositoryImpl(
//            ItemService itemService,
            ItemsDatabase itemsDatabase) {
//        this.itemService = itemService;
        this.itemsDatabase = itemsDatabase;
    }

    @Override
    public Single<? extends List<? extends ITransactions>> retrieveItemsList() {
        // List of Priorities
//        Single<? extends List<? extends ITransactions>> backendSource = retrieveItemsListFromBackend();
        Single<? extends List<? extends ITransactions>> storageSource = retrieveItemsListFromStorage();
        Single<? extends List<? extends ITransactions>> cacheSource = retrieveItemsListFromCache();

        return Single.concat(
//                backendSource,
                storageSource, cacheSource)
                .filter(source -> !source.isEmpty())
                .first(memoryCache);
    }

//    private Single<? extends List<? extends ITransactions>> retrieveItemsListFromBackend() {
//        return imdbService.getItemsList()
//             .map(itemsListBackend -> itemsListBackend.getItemsList())
//             .doOnSuccess(itemsList -> {
//                // Updates the internal cache
//                saveItemsListToCache(itemsList);
//                // Save the content into the database
//                for (ITransactions item: itemsList) {
//                    Log.v(TAG, "Trying to save " + item + " into the database");
//                    itemsDatabase.itemDao().upsert(new ITransactionsImpl(item));
//                }
//             }).onErrorResumeNext(throwable -> {
//                 Log.e(TAG, "Error retrieving data from backend", throwable);
//                 return Single.just(new ArrayList<>());
//             });
//    }

    private Single<? extends List<? extends ITransactions>> retrieveItemsListFromCache() {
        return Single.just(memoryCache);
    }

    private Single<? extends List<? extends ITransactions>> retrieveItemsListFromStorage() {
        return itemsDatabase.itemDao().getAllItems()
                .doOnSuccess(itemsList -> saveItemsListToCache(itemsList))
                .onErrorResumeNext(throwable -> {
                   Log.e(TAG, "Error retrieving data from the database ", throwable);
                   return Single.just(new ArrayList<>());
                });
    }

    private void saveItemsListToCache(List<? extends ITransactions> newItemsList) {
        this.memoryCache = newItemsList;
    }
}
