package com.jiahaoliuliu.chutoro.devicelayer;


import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.List;

import io.reactivex.Observable;

/**
 * The common transaction provider for all the banks.
 * This is the main source which provides the list of transactions
 * to the upper layers
 */
public class CommonTransactionsProvider {

    private final TransactionsProvider adcbTransactionsProvider;
    private final TransactionsProvider najmTransactionsProvider;
    private final TransactionsProvider hsbcTransactionsProvider;

    public CommonTransactionsProvider(
            TransactionsProvider adcbTransactionsProvider,
            TransactionsProvider najmTransactionsProvider,
            TransactionsProvider hsbcTransactionsProvider) {
        this.adcbTransactionsProvider = adcbTransactionsProvider;
        this.najmTransactionsProvider = najmTransactionsProvider;
        this.hsbcTransactionsProvider = hsbcTransactionsProvider;
    }

    public Observable<Transaction> provideTransactions() {
        return adcbTransactionsProvider.provideTransactions();
//        return Single.zip(
//                adcbTransactionsProvider.provideTransactions(),
//                najmTransactionsProvider.provideTransactions(),
//                hsbcTransactionsProvider.provideTransactions(),
//                (transactions, transactions2, transactions3) -> {
//                    transactions.addAll(transactions2);
//                    transactions.addAll(transactions3);
//                    return transactions;
//                });
    }
}
