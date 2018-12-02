package com.jiahaoliuliu.chutoro.ui.addtransaction

interface AddTransactionContract {
    interface View {

    }

    interface Presenter {
        fun setView(view: View)

        fun addTransactionIfCorrect(
                destination: String,
                source: String,
                quantity: String,
                currency: String)
    }
}