package com.jiahaoliuliu.chutoro.ui.itemslist;

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository;

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
    ItemsListContract.Model provideModel(ITransactionsRepository ITransactionsRepository) {
        return new ItemsListModel(ITransactionsRepository);
    }
}
