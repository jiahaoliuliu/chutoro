package com.jiahaoliuliu.chutoro.usecase

import com.jiahaoliuliu.chutoro.datalayer.transactionsrepository.ITransactionsRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddTransactionUseCaseTest {

    @Mock
    lateinit var transactionsRepository: ITransactionsRepository

    private lateinit var addTransactionUseCase: AddTransactionUseCase

    @Before
    fun setup() {
        addTransactionUseCase = AddTransactionUseCase(transactionsRepository)
    }

    @Test
    fun testParseQuantity_Success() {
        // Prepare the data
        val quantityString = "100.00"

        // Invoke the method
        val quantity = addTransactionUseCase.parseQuantity(quantityString)

        // Check the result
        assertEquals(10000, quantity)
    }
}