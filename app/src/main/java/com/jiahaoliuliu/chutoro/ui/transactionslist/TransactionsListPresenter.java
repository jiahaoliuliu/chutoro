package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TransactionsListPresenter implements TransactionsListContract.Presenter {

    private static final String TAG = "TransactionsListPresenter";

    private TransactionsListContract.View view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final RetrieveTransactionsListUseCase retrieveTransactionsListUseCase;

//    @Inject
    public TransactionsListPresenter(RetrieveTransactionsListUseCase retrieveTransactionsListUseCase) {
        this.retrieveTransactionsListUseCase = retrieveTransactionsListUseCase;
    }

    @Override
    public void setView(TransactionsListContract.View view) {
        this.view = view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        return retrieveTransactionsListUseCase.execute();
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
