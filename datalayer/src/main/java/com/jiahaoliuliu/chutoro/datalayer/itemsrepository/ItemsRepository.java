package com.jiahaoliuliu.chutoro.datalayer.itemsrepository;

import com.jiahaoliuliu.chutoro.entity.Item;

import java.util.List;

import io.reactivex.Single;

/**
 * A simple repository for the items
 */
public interface ItemsRepository {

    /**
     * Retrieve a list of items either from network, either from the
     * temporal memory, either from the database
     * @return a list of items
     */
    Single<? extends List<? extends Item>> retrieveItemsList();
}
