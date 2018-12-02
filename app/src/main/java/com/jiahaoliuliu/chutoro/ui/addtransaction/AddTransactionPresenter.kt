package com.jiahaoliuliu.chutoro.ui.addtransaction

import android.text.TextUtils

class AddTransactionPresenter: AddTransactionContract.Presenter {

    private var view: AddTransactionContract.View? = null

    override fun setView(view: AddTransactionContract.View) {
        this.view = view
    }

    override fun addTransactionIfCorrect(
            destination: String, source: String, quantity: String, currency: String) {
        if (isAllDataValid(destination, source, quantity, currency)) {

        }
    }

    private fun isAllDataValid(destination: String, source: String, quantity: String,
                               currency: String): Boolean {
        return isDestinationValid(destination) && isSourceValid(source) &&
                isQuantityValid(quantity) && isCurrencyValid(currency)
    }

    private fun isDestinationValid(destination: String): Boolean {
        return !TextUtils.isEmpty(destination)
    }

    private fun isSourceValid(source: String): Boolean {
        return !TextUtils.isEmpty(source)
    }

    private fun isQuantityValid(quantity: String): Boolean {
        return quantity.toFloatOrNull() != null
    }

    private fun isCurrencyValid(currency: String): Boolean {
        return !TextUtils.isEmpty(currency)
    }

}