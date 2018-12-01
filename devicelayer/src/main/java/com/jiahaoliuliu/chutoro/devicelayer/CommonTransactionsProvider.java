package com.jiahaoliuliu.chutoro.devicelayer;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import io.reactivex.Single;

/**
 * The common transaction provider for all the banks.
 * This is the main source which provides the list of transactions
 * to the upper layers
 */
public class CommonTransactionsProvider {

    private final TransactionsProvider adcbTransactionsProvider;

    public CommonTransactionsProvider(TransactionsProvider adcbTransactionsProvider) {
        this.adcbTransactionsProvider = adcbTransactionsProvider;
    }

    public Single<? extends List<? extends ITransaction>> provideTransactionsList() {
        return adcbTransactionsProvider.provideTransactions();
    }
}
