package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TransactionsRepository implements ITransactionsRepository {

    private static final String TAG = "TransactionsRepository";
    private final TransactionsDatabase transactionsDatabase;
    private final CommonTransactionsProvider commonTransactionsProvider;

    public TransactionsRepository(TransactionsDatabase transactionsDatabase,
                                  CommonTransactionsProvider commonTransactionsProvider) {
        this.transactionsDatabase = transactionsDatabase;
        this.commonTransactionsProvider = commonTransactionsProvider;
    }

    @Override
    public LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        updateTransactionsList();
        return transactionsDatabase.transactionsDao().getAllTransactions();
    }

    private void updateTransactionsList() {
        commonTransactionsProvider.provideTransactionsList().subscribeOn(Schedulers.io())
                .subscribe(transactionsList -> {
//                    transactionsDatabase.transactionsDao().upsert(transactionsList);
                }, throwable -> {
                    Log.e(TAG, "Error getting the transactions List");
                });
    }

//    private Single<? extends List<? extends ITransaction>> retrieveTransactionsListFromStorage() {
//        return transactionsDatabase.transactionsDao().getAllTransactions()
//                .doOnSuccess(transactionsList -> saveTransactionsListToCache(transactionsList))
//                .onErrorResumeNext(throwable -> {
//                   Log.e(TAG, "Error retrieving data from the database ", throwable);
//                   return Single.just(new ArrayList<>());
//                });
//    }
}
