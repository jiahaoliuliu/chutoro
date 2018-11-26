package com.jiahaoliuliu.chutoro.ui.itemslist;

import com.jiahaoliuliu.chutoro.datalayer.itemsrepository.ItemsRepository;
import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ItemsListModel implements ItemsListContract.Model {

    private final ItemsRepository itemsRepository;

    @Inject
    public ItemsListModel(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Single<? extends List<? extends ITransaction>> retrieveItemsList() {
        return itemsRepository.retrieveItemsList();
    }
}
