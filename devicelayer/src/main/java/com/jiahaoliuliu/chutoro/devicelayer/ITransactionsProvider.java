package com.jiahaoliuliu.chutoro.devicelayer;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import io.reactivex.Single;

public interface ITransactionsProvider {

    /**
     * Provide all the transactions
     * @return
     */
    Single<? extends List<? extends ITransaction>> provideTransactions();
}
