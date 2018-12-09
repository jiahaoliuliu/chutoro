package com.jiahaoliuliu.chutoro.entity

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class TransactionBuilderTest {

    private lateinit var transactionBuilder: TransactionBuilder

    @Before
    fun setup() {
        transactionBuilder = TransactionBuilder()
    }

    @Test(expected = IllegalStateException::class)
    fun emptyBuild() {
        // Prepare the data

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }
}