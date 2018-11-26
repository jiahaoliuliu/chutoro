package com.jiahaoliuliu.chutoro.ui.itemslist;

import com.jiahaoliuliu.chutoro.entity.ITransactions;

import java.util.List;

import io.reactivex.Single;

/**
 * Contract for MVP
 */
public interface ItemsListContract {

    interface View {

        /**
         * Show the list of items on the screen
         * @param itemsList
         */
        void showItemsList(List<? extends ITransactions> itemsList);
    }

    interface Presenter {
        /**
         * Set the current view
         * @param view
         */
        void setView(View view);

        /**
         * Method used to retrieve a list of items
         * @return Single of a list of items
         */
        void retrieveItemsList();

        /**
         * Dispose all the retained memory
         */
        void dispose();
    }

    interface Model {
        /**
         * Method used to retrieve a list of items
         * @return Single of a list of items
         */
        Single<? extends List<? extends ITransactions>> retrieveItemsList();
    }
}
