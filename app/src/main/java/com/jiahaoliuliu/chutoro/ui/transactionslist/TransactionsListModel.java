package com.jiahaoliuliu.chutoro.ui.transactionslist;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TransactionsListModel implements TransactionsListContract.Model {

    private final ITransactionsRepository ITransactionsRepository;

    @Inject
    public TransactionsListModel(ITransactionsRepository ITransactionsRepository) {
        this.ITransactionsRepository = ITransactionsRepository;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        return ITransactionsRepository.retrieveTransactionsList();
    }
}
