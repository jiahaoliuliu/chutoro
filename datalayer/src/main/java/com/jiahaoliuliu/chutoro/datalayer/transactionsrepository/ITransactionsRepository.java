package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.ITransactionShown;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
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
    LiveData<? extends List<? extends ITransactionShown>> retrieveTransactionsList();

    /**
     * Method used to update the transactions list. More than a method, it is an
     * order. This is because the method retrieveTransactionsList uses LiveData
     * to fetch the data from the database. As soon as the transactions list in
     * the database get update, LiveData will also update the front-end. So there
     * is not need to return anything.
     */
    Completable updateTransactionsList();

    /**
     * Adding a new transaction
     * @param transaction
     * @return
     */
    Single<Boolean> addTransaction(ITransaction transaction);

}
