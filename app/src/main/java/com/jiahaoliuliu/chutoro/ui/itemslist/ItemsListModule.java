package com.jiahaoliuliu.chutoro.ui.itemslist;

import com.jiahaoliuliu.chutoro.datalayer.itemsrepository.ItemsRepository;

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
