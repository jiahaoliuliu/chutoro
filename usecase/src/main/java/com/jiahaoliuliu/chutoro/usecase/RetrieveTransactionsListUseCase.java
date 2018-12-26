package com.jiahaoliuliu.chutoro.usecase;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;
import com.jiahaoliuliu.chutoro.storagelayer.ITransactionShown;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RetrieveTransactionsListUseCase {

    private final ITransactionsRepository ITransactionsRepository;

    public RetrieveTransactionsListUseCase(ITransactionsRepository ITransactionsRepository) {
        this.ITransactionsRepository = ITransactionsRepository;
    }

    public LiveData<? extends List<? extends ITransactionShown>> execute() {
        return ITransactionsRepository.retrieveTransactionsList();
    }
}
