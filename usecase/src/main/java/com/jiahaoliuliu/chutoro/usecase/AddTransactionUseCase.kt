package com.jiahaoliuliu.chutoro.usecase

import android.text.TextUtils
import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository
import com.jiahaoliuliu.chutoro.entity.Transaction
import io.reactivex.Single
import java.lang.IllegalArgumentException

class AddTransactionUseCase(private val transactionsRepository: ITransactionsRepository) {

    // TODO: Create a special class for Quantity
    fun execute(destination: String, source: String, quantityString: String, currency: String, date: Long): Single<Boolean> {
        try {
            val transaction = Transaction.Builder()
        }
//        if (isAllDataValid(destination, source, quantityString, currency, date)) {
//            // Quantity
//            val quantity = parseQuantity(quantityString)
//
//            val transaction = Transaction(quantity, currency, source, destination, date)
//            return transactionsRepository.addTransaction(transaction)
//        } else {
//            return Single.error(IllegalArgumentException("The parameters are not correct"))
//        }
    }

    fun parseQuantity(quantityString: String): Int {
        return (quantityString.toFloat() * 100.0).toInt()
    }

    private fun isAllDataValid(destination: String, source: String, quantity: String,
                               currency: String, date: Long): Boolean {
        return isDestinationValid(destination) && isSourceValid(source) &&
                isQuantityValid(quantity) && isCurrencyValid(currency) && isDateValid(date)
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

    private fun isDateValid(date: Long): Boolean {
        return date > 0
    }

}