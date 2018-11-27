package com.jiahaoliuliu.chutoro.devicelayer;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * The common transaction provider for all the banks.
 * This is the main source which provides the list of transactions
 * to the upper layers
 */
public class CommonTransactionsProvider {

    private final ITransactionsProvider adcbTransactionsProvider;

    public CommonTransactionsProvider(ITransactionsProvider adcbTransactionsProvider) {
        this.adcbTransactionsProvider = adcbTransactionsProvider;
    }

    public Single<List<ITransaction>> provideTransactionsList() {
        return adcbTransactionsProvider.provideTransactions();
    }
}
