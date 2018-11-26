package com.jiahaoliuliu.chutoro.ui;

import com.jiahaoliuliu.chutoro.ui.itemslist.ItemsListActivity;
import com.jiahaoliuliu.chutoro.ui.itemslist.ItemsListModule;
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.TransactionsRepositoryModule;
import com.jiahaoliuliu.chutoro.networklayer.NetworkModule;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class, ItemsListModule.class,
        TransactionsRepositoryModule.class,
        NetworkModule.class, TransactionsDatabaseModule.class
})
@Singleton
public interface MainComponent {
    void inject(ItemsListActivity itemsListActivity);
}
