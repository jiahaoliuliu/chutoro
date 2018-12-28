package com.jiahaoliuliu.chutoro.ui.transactionslist;

import com.jiahaoliuliu.chutoro.storagelayer.ui.ITransactionShown;
import com.jiahaoliuliu.chutoro.usecase.RetrieveTransactionsListUseCase;
import com.jiahaoliuliu.chutoro.usecase.UpdateTransactionsUseCase;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
    public LiveData<? extends List<? extends ITransactionShown>> retrieveTransactionsList() {
        return retrieveTransactionsListUseCase.execute();
    }

    @Override
    public void updateTransactionsList() {
        updateTransactionsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    // Nothing to do here
                    Timber.v("Trying to update the transactions list");
                }

                @Override
                public void onComplete() {
                    Timber.v("Transactions list completed");
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e,"Error updating the transactions list");
                }
            });
    }

    @Override
    public void dispose() {
        compositeDisposable.clear();
    }
}
