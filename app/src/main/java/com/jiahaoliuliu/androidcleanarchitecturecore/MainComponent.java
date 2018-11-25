package com.jiahaoliuliu.androidcleanarchitecturecore;

import com.jiahaoliuliu.androidcleanarchitecturecore.itemslist.ItemsListActivity;
import com.jiahaoliuliu.androidcleanarchitecturecore.itemslist.ItemsListModule;
import com.jiahaoliuliu.datalayer.itemsrepository.ItemsRepositoryModule;
import com.jiahaoliuliu.networklayer.NetworkModule;
import com.jiahaoliuliu.storagelayer.ItemsDatabaseModule;

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
