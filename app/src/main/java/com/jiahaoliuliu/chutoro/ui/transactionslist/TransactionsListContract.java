package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.ITransactionShown;

import java.util.List;

import io.reactivex.Single;

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
