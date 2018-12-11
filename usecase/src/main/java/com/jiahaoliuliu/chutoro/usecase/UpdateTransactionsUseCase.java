package com.jiahaoliuliu.chutoro.usecase;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

public class UpdateTransactionsUseCase {

    private final ITransactionsRepository transactionsRepository;

    public UpdateTransactionsUseCase(ITransactionsRepository transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    public void execute() {
        transactionsRepository.updateTransactionsList();
    }
}
