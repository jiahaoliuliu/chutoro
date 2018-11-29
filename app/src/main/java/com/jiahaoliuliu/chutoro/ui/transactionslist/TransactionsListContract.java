package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.arch.lifecycle.LiveData;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

import io.reactivex.Single;

/**
 * Contract for MVP
 */
public interface TransactionsListContract {

    interface View {

//        /**
//         * Show the list of transactions on the screen
//         * @param transactionsList
//         */
//        void showTransactionsList(List<? extends ITransaction> transactionsList);
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
        LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList();

        /**
         * Dispose all the retained memory
         */
        void dispose();
    }

    interface Model {
        /**
         * Method used to retrieve a list of transactions
         * @return Single of a list of transactions
         */
        LiveData<? extends List<? extends ITransaction>> retrieveTransactionsList();
    }
}
