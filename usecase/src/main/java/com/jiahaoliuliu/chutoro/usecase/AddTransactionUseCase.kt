package com.jiahaoliuliu.chutoro.usecase

import android.text.TextUtils
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository
import com.jiahaoliuliu.chutoro.entity.Transaction

class AddTransactionUseCase(private val transactionsRepository: ITransactionsRepository) {

    fun execute(destination: String, source: String, quantity: String, currency: String) {
        if (isAllDataValid(destination, source, quantity, currency)) {
            val transaction = Transaction()
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