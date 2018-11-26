package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class TransactionsRepository implements ITransactionsRepository {

    private static final String TAG = "TransactionsRepository";
    private final TransactionsDatabase transactionsDatabase;
    // Temporal memory for the transactions list
    private List<? extends ITransaction> memoryCache = new ArrayList<>();

    public TransactionsRepository(TransactionsDatabase transactionsDatabase) {
        this.transactionsDatabase = transactionsDatabase;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        // List of Priorities
        Single<? extends List<? extends ITransaction>> storageSource = retrieveTransactionsListFromStorage();
        Single<? extends List<? extends ITransaction>> cacheSource = retrieveTransactionsListFromCache();

        return Single.concat(
                storageSource, cacheSource)
                .filter(source -> !source.isEmpty())
                .first(memoryCache);
    }

    private Single<? extends List<? extends ITransaction>> retrieveTransactionsListFromCache() {
        return Single.just(memoryCache);
    }

    private Single<? extends List<? extends ITransaction>> retrieveTransactionsListFromStorage() {
        return transactionsDatabase.transactionsDao().getAllTransactions()
                .doOnSuccess(transactionsList -> saveTransactionsListToCache(transactionsList))
                .onErrorResumeNext(throwable -> {
                   Log.e(TAG, "Error retrieving data from the database ", throwable);
                   return Single.just(new ArrayList<>());
                });
    }

    private void saveTransactionsListToCache(List<? extends ITransaction> newTransactionsList) {
        this.memoryCache = newTransactionsList;
    }
}
