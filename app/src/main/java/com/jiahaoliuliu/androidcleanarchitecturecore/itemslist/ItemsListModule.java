package com.jiahaoliuliu.androidcleanarchitecturecore.itemslist;

import com.jiahaoliuliu.datalayer.itemsrepository.ItemsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemsListModule {

    @Provides
    @Singleton
    ItemsListContract.Presenter providePresenter(ItemsListContract.Model model) {
        return new ItemsListPresenter(model);
    }

    @Provides
    @Singleton
    ItemsListContract.Model provideModel(ItemsRepository itemsRepository) {
        return new ItemsListModel(itemsRepository);
    }
}
