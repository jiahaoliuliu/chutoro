package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TransactionsRepository implements ITransactionsRepository {

    private static final String TAG = "TransactionsRepository";
    private final TransactionsDatabase transactionsDatabase;
    private final CommonTransactionsProvider commonTransactionsProvider;
    private LiveData<? extends List<? extends ITransaction>> allTransactions;

    public TransactionsRepository(TransactionsDatabase transactionsDatabase,
                                  CommonTransactionsProvider commonTransactionsProvider) {
        this.transactionsDatabase = transactionsDatabase;
        this.commonTransactionsProvider = commonTransactionsProvider;
        allTransactions = transactionsDatabase.transactionsDao().getAllTransactions();
    }

    @Override
    public LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        updateTransactionsList();
        return allTransactions;
    }

    @Override
    public Single<Boolean> addTransaction(ITransaction transaction) {
        return Single.fromCallable(
                () -> transactionsDatabase.transactionsDao().insertIfDoesNotExist(transaction));
    }

    private void updateTransactionsList() {
        commonTransactionsProvider.provideTransactionsList()
            .subscribeOn(Schedulers.io())
            .subscribe(transactionsList -> {
                transactionsDatabase.transactionsDao().insertIfDoesNotExist(transactionsList);
            }, throwable -> {
                Log.e(TAG, "Error getting the transactions List", throwable);
            });
    }
}
