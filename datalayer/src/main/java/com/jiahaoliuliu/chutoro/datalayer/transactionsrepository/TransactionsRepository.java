package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.ITransactionShown;
import com.jiahaoliuliu.chutoro.storagelayer.MainDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TransactionsRepository implements ITransactionsRepository {

    private final MainDatabase mainDatabase;
    private final CommonTransactionsProvider commonTransactionsProvider;
    private LiveData<? extends List<? extends ITransactionShown>> allTransactions;

    public TransactionsRepository(MainDatabase mainDatabase,
                                  CommonTransactionsProvider commonTransactionsProvider) {
        this.mainDatabase = mainDatabase;
        this.commonTransactionsProvider = commonTransactionsProvider;
        allTransactions = mainDatabase.transactionsDao().getAllTransactions();
    }

    @Override
    public LiveData<? extends List<? extends ITransactionShown>> retrieveTransactionsList() {
        return allTransactions;
    }

    @Override
    public Single<Boolean> addTransaction(ITransaction transaction) {
        return Single.fromCallable(
                () -> mainDatabase.transactionsDao().insertIfDoesNotExist(transaction));
    }

    @Override
    public Completable updateTransactionsList() {
        return Completable.fromObservable(commonTransactionsProvider.provideTransactions()
                .doOnNext(transaction -> mainDatabase.transactionsDao().insertIfDoesNotExist(transaction)));
    }
}
