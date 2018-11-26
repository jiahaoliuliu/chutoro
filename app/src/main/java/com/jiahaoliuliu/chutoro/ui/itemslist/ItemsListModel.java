package com.jiahaoliuliu.chutoro.ui.itemslist;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ItemsListModel implements ItemsListContract.Model {

    private final ITransactionsRepository ITransactionsRepository;

    @Inject
    public ItemsListModel(ITransactionsRepository ITransactionsRepository) {
        this.ITransactionsRepository = ITransactionsRepository;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> retrieveItemsList() {
        return ITransactionsRepository.retrieveTransactionsList();
    }
}
