package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
//import com.jiahaoliuliu.chutoro.networklayer.TransactionService;
import com.jiahaoliuliu.chutoro.storagelayer.ItemsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ItemsRepositoryImpl implements ItemsRepository {

    private static final String TAG = "ItemsRepositoryImpl";

//    private final TransactionService itemService;
    private final ItemsDatabase itemsDatabase;
    // Temporal memory for the items list
    private List<? extends ITransaction> memoryCache = new ArrayList<>();

    public ItemsRepositoryImpl(
//            TransactionService itemService,
            ItemsDatabase itemsDatabase) {
//        this.itemService = itemService;
        this.itemsDatabase = itemsDatabase;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> retrieveItemsList() {
        // List of Priorities
//        Single<? extends List<? extends ITransaction>> backendSource = retrieveItemsListFromBackend();
        Single<? extends List<? extends ITransaction>> storageSource = retrieveItemsListFromStorage();
        Single<? extends List<? extends ITransaction>> cacheSource = retrieveItemsListFromCache();

        return Single.concat(
//                backendSource,
                storageSource, cacheSource)
                .filter(source -> !source.isEmpty())
                .first(memoryCache);
    }

//    private Single<? extends List<? extends ITransaction>> retrieveItemsListFromBackend() {
//        return imdbService.getItemsList()
//             .map(itemsListBackend -> itemsListBackend.getItemsList())
//             .doOnSuccess(itemsList -> {
//                // Updates the internal cache
//                saveItemsListToCache(itemsList);
//                // Save the content into the database
//                for (ITransaction item: itemsList) {
//                    Log.v(TAG, "Trying to save " + item + " into the database");
//                    itemsDatabase.itemDao().upsert(new Transaction(item));
//                }
//             }).onErrorResumeNext(throwable -> {
//                 Log.e(TAG, "Error retrieving data from backend", throwable);
//                 return Single.just(new ArrayList<>());
//             });
//    }

    private Single<? extends List<? extends ITransaction>> retrieveItemsListFromCache() {
        return Single.just(memoryCache);
    }

    private Single<? extends List<? extends ITransaction>> retrieveItemsListFromStorage() {
        return itemsDatabase.itemDao().getAllItems()
                .doOnSuccess(itemsList -> saveItemsListToCache(itemsList))
                .onErrorResumeNext(throwable -> {
                   Log.e(TAG, "Error retrieving data from the database ", throwable);
                   return Single.just(new ArrayList<>());
                });
    }

    private void saveItemsListToCache(List<? extends ITransaction> newItemsList) {
        this.memoryCache = newItemsList;
    }
}
