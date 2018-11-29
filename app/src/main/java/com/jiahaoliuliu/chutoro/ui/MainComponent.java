package com.jiahaoliuliu.chutoro.ui;

import com.jiahaoliuliu.chutoro.devicelayer.di.DeviceLayerModule;
import com.jiahaoliuliu.chutoro.ui.transactionslist.TransactionsListActivity;
import com.jiahaoliuliu.chutoro.ui.transactionslist.TransactionsListModule;
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.TransactionsRepositoryModule;
import com.jiahaoliuliu.chutoro.networklayer.NetworkModule;
import com.jiahaoliuliu.chutoro.storagelayer.TransactionsDatabaseModule;
import com.jiahaoliuliu.chutoro.usecase.di.UseCaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class, TransactionsListModule.class,
        TransactionsRepositoryModule.class,
        NetworkModule.class, TransactionsDatabaseModule.class,
        DeviceLayerModule.class, UseCaseModule.class
})
@Singleton
public interface MainComponent {
    void inject(TransactionsListActivity transactionsListActivity);
}
