package com.jiahaoliuliu.chutoro.ui.transactionslist;

import com.jiahaoliuliu.chutoro.storagelayer.ui.ITransactionShown;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Contract for MVP
 */
public interface TransactionsListContract {

    interface View {

    }

    interface Presenter {
        /**
         * Set the current view
         * @param view
         */
        void setView(View view);

        /**
         * Method used to retrieve a list of transactions
         * @return Single of a list of transactions
         */
        LiveData<? extends List<? extends ITransactionShown>> retrieveTransactionsList();

        /**
         * Method used to update the existing transactions list
         */
        void updateTransactionsList();

        /**
         * Dispose all the retained memory
         */
        void dispose();
    }
}
