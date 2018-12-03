package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import io.reactivex.Single;

/**
 * A simple repository for the transactions
 */
public interface ITransactionsRepository {

    /**
     * Retrieve a list of transactions either from the device, either from the
     * temporal memory, either from the database
     * @return a list of transactions
     */
    LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList();

    /**
     * Adding a new transaction
     * @param transaction
     * @return
     */
    Single<Boolean> addTransaction(ITransaction transaction);
}
