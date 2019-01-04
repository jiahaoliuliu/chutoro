package com.jiahaoliuliu.chutoro.ui.addtransaction

import com.jiahaoliuliu.chutoro.entity.Currency
import com.jiahaoliuliu.chutoro.entity.Source
import java.lang.IllegalArgumentException

interface AddTransactionContract {
    interface View {
        /**
         * Action to perform when the insertion was correct
         */
        fun onInsertionCorrect()

        /**
         * Show the insertion was incorrect
         */
        fun showInsertionError(exception: IllegalArgumentException)
    }

    interface Presenter {
        fun setView(view: View)

        fun addTransactionIfCorrect(
                destination: String,
                source: Source,
                quantity: String,
                currency: Currency,
                date: Long)

        fun dispose()
    }
}