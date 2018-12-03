package com.jiahaoliuliu.chutoro.usecase

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository
import com.jiahaoliuliu.chutoro.entity.Currency
import com.jiahaoliuliu.chutoro.entity.TransactionBuilder
import io.reactivex.Single
import java.lang.IllegalArgumentException

class AddTransactionUseCase(private val transactionsRepository: ITransactionsRepository) {

    fun execute(destination: String, source: String, quantity: String, currency: Currency, date: Long): Single<Boolean> {
        try {
            val transaction = TransactionBuilder(source, destination, date)
                    .setQuantity(quantity)
                    .setCurrency(currency)
                    .setDate(date)
                    .build()
            return transactionsRepository.addTransaction(transaction)
        } catch (illegalArgumentException: IllegalArgumentException) {
            return Single.error(illegalArgumentException)
        }
    }

}