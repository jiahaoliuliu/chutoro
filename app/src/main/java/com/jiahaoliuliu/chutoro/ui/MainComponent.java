package com.jiahaoliuliu.chutoro.ui;

import com.jiahaoliuliu.chutoro.ui.itemslist.ItemsListActivity;
import com.jiahaoliuliu.chutoro.ui.itemslist.ItemsListModule;
import com.jiahaoliuliu.chutoro.datalayer.itemsrepository.ItemsRepositoryModule;
import com.jiahaoliuliu.chutoro.networklayer.NetworkModule;
import com.jiahaoliuliu.chutoro.storagelayer.ItemsDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class, ItemsListModule.class,
        ItemsRepositoryModule.class,
        NetworkModule.class, ItemsDatabaseModule.class
})
@Singleton
public interface MainComponent {
    void inject(ItemsListActivity itemsListActivity);
}
