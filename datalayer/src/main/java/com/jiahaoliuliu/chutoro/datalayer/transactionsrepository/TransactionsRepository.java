package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TransactionsRepository implements ITransactionsRepository {

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
        return allTransactions;
    }

    @Override
    public Single<Boolean> addTransaction(ITransaction transaction) {
        return Single.fromCallable(
                () -> transactionsDatabase.transactionsDao().insertIfDoesNotExist(transaction));
    }

    @Override
    public Completable updateTransactionsList() {
        return Completable.fromObservable(commonTransactionsProvider.provideTransactions()
                .doOnNext(transaction -> transactionsDatabase.transactionsDao().insertIfDoesNotExist(transaction)));
    }
}
