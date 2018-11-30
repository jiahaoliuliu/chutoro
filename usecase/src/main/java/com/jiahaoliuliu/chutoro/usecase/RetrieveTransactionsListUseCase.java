package com.jiahaoliuliu.chutoro.usecase;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

import java.util.List;

public class RetrieveTransactionsListUseCase {

    private final ITransactionsRepository ITransactionsRepository;

    public RetrieveTransactionsListUseCase(ITransactionsRepository ITransactionsRepository) {
        this.ITransactionsRepository = ITransactionsRepository;
    }

    public LiveData<? extends List<? extends ITransaction>> execute() {
        return ITransactionsRepository.retrieveTransactionsList();
    }
}
