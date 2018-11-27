package com.jiahaoliuliu.chutoro.devicelayer;

import android.content.Context;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Specific transactions provider for ABCD Bank
 * (Abu Dhabi Commercial Bank)
 */
public class ADCBTransactionsProvider implements ITransactionsProvider{

    /**
     * The context is needed to access to the content providers
     */
    private final Context context;

    public ADCBTransactionsProvider(Context context) {
        this.context = context;
    }

    @Override
    public Single<List<ITransaction>> provideTransactions() {
        // TODO: Implement this
        return Single.just(new ArrayList<>());
    }
}
