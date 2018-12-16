package com.jiahaoliuliu.chutoro.usecase;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

import io.reactivex.Completable;

public class UpdateTransactionsUseCase {

    private final ITransactionsRepository transactionsRepository;

    public UpdateTransactionsUseCase(ITransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public Completable execute() {
        return transactionsRepository.updateTransactionsList();
    }
}
