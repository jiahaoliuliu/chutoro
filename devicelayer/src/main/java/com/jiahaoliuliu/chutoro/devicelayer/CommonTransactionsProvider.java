package com.jiahaoliuliu.chutoro.devicelayer;

import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.List;

import io.reactivex.Single;

/**
 * The common transaction provider for all the banks.
 * This is the main source which provides the list of transactions
 * to the upper layers
 */
public class CommonTransactionsProvider {

    private final TransactionsProvider adcbTransactionsProvider;
    private final TransactionsProvider najmTransactionsProvider;

    public CommonTransactionsProvider(TransactionsProvider adcbTransactionsProvider,
                                      TransactionsProvider najmTransactionsProvider) {
        this.adcbTransactionsProvider = adcbTransactionsProvider;
        this.najmTransactionsProvider = najmTransactionsProvider;
    }

    public Single<List<Transaction>> provideTransactionsList() {
        return Single.zip(
                adcbTransactionsProvider.provideTransactions(),
                najmTransactionsProvider.provideTransactions(),
                (transactions, transactions2) -> {
                    transactions.addAll(transactions2);
                    return transactions;
                });
    }
}
