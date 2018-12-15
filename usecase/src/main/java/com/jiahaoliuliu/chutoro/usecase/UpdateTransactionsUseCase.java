package com.jiahaoliuliu.chutoro.usecase;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

import io.reactivex.Single;

public class UpdateTransactionsUseCase {

    private final ITransactionsRepository transactionsRepository;

    public UpdateTransactionsUseCase(ITransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Single execute() {
        return transactionsRepository.updateTransactionsList();
    }
}
