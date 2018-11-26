package com.jiahaoliuliu.chutoro.ui.itemslist;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ItemsListPresenter implements ItemsListContract.Presenter {

    private static final String TAG = "ItemsListPresenter";

    private final ItemsListContract.Model model;

    private ItemsListContract.View view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ItemsListPresenter(ItemsListContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(ItemsListContract.View view) {
        this.view = view;
    }

    @Override
    public void retrieveItemsList() {
        compositeDisposable.add(model.retrieveItemsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                itemsList -> view.showItemsList(itemsList),
                throwable -> {
                    // TODO: Show the error on the screen
                    Log.e(TAG, "Error getting the list of the items from backend ", throwable);
                }));
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }
}
