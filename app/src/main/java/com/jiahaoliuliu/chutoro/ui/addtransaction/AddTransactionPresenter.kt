package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.text.TextUtils
import com.jiahaoliuliu.chutoro.usecase.AddTransactionUseCase

class AddTransactionPresenter(private val addTransactionUseCase: AddTransactionUseCase):
        AddTransactionContract.Presenter {

    private var view: AddTransactionContract.View? = null

    override fun setView(view: AddTransactionContract.View) {
        this.view = view
    }

    override fun addTransactionIfCorrect(
            destination: String, source: String, quantity: String, currency: String) {
        addTransactionUseCase.execute(destination, source, quantity, currency)
    }

}