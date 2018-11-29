package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TransactionsListPresenter implements TransactionsListContract.Presenter {

    private static final String TAG = "TransactionsListPresenter";

    private final TransactionsListContract.Model model;

    private TransactionsListContract.View view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public TransactionsListPresenter(TransactionsListContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(TransactionsListContract.View view) {
        this.view = view;
    }

    @SuppressLint("LongLogTag")
    @Override
    public LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList() {
        return model.retrieveTransactionsList();
//        compositeDisposable.add(model.retrieveTransactionsList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                transactionsList -> view.showTransactionsList(transactionsList),
//                throwable -> {
//                    // TODO: Show the error on the screen
//                    Log.e(TAG, "Error getting the list of the transactions from backend ", throwable);
//                }));
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
