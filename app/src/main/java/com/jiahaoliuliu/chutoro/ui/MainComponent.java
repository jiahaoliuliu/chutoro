package com.jiahaoliuliu.chutoro.ui;

import com.jiahaoliuliu.chutoro.datalayer.di.RepositoryModule;
import com.jiahaoliuliu.chutoro.devicelayer.di.DeviceLayerModule;
import com.jiahaoliuliu.chutoro.storagelayer.di.StorageModule;
import com.jiahaoliuliu.chutoro.ui.addtransaction.AddTransactionActivity;
import com.jiahaoliuliu.chutoro.ui.addtransaction.AddTransactionModule;
import com.jiahaoliuliu.chutoro.ui.transactionslist.TransactionsListActivity;
import com.jiahaoliuliu.chutoro.ui.transactionslist.TransactionsListModule;
import com.jiahaoliuliu.chutoro.networklayer.NetworkModule;
import com.jiahaoliuliu.chutoro.usecase.di.UseCaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class,
        TransactionsListModule.class, AddTransactionModule.class,
        UseCaseModule.class, RepositoryModule.class,
        NetworkModule.class, StorageModule.class,
        DeviceLayerModule.class
})
@Singleton
public interface MainComponent {

    void inject(TransactionsListActivity transactionsListActivity);

    void inject(AddTransactionActivity addTransactionActivity);
}
