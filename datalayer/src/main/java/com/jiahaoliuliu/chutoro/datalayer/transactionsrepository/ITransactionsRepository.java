package com.jiahaoliuliu.chutoro.datalayer.transactionsrepository;

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
    Single<? extends List<? extends ITransaction>> retrieveTransactionsList();
}
