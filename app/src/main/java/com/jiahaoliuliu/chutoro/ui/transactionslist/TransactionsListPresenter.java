package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;
import com.jiahaoliuliu.chutoro.usecase.UpdateTransactionsUseCase;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class TransactionsListPresenter implements TransactionsListContract.Presenter {

    private TransactionsListContract.View view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final RetrieveTransactionsListUseCase retrieveTransactionsListUseCase;
    private final UpdateTransactionsUseCase updateTransactionsUseCase;

    public TransactionsListPresenter(RetrieveTransactionsListUseCase retrieveTransactionsListUseCase,
                                     UpdateTransactionsUseCase updateTransactionsUseCase) {
        this.retrieveTransactionsListUseCase = retrieveTransactionsListUseCase;
        this.updateTransactionsUseCase = updateTransactionsUseCase;
    }

    @Override
    public void setView(TransactionsListContract.View view) {
        this.view = view;
    }

    @Override
    public LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        return retrieveTransactionsListUseCase.execute();
    }

    @Override
    public void updateTransactionsList() {
        compositeDisposable.add(updateTransactionsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribe(transactionsList -> {
                    Timber.v("Transactions list updated " + transactionsList.toString());
                },
                throwable -> {
                    Timber.e((Throwable)throwable, "Error updating the transactions list");
                }));
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }
}
