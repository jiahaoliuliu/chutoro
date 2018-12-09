package com.jiahaoliuliu.chutoro.entity

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class TransactionBuilderTest {

    companion object {
        private const val SMS_ID = 1234567L
        private const val QUANTITY = 5000
        private val CURRENCY_AED = Currency.AED.toString()
        private val SOURCE_ADCB = Source.ADCB.toString()
        private const val DESTINATION = "Carrefour"
        private const val DATE = 1544369694000L
    }

    private lateinit var transactionBuilder: TransactionBuilder

    @Before
    fun setup() {
        transactionBuilder = TransactionBuilder()
    }

    @Test(expected = IllegalStateException::class)
    fun testBuild_empty() {
        // Prepare the data

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalStateException::class)
    fun testBuild_withQuantity() {
        // Prepare the data
        transactionBuilder.setQuantity(QUANTITY)

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalStateException::class)
    fun testBuild_withQuantityCurrency() {
        // Prepare the data
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalStateException::class)
    fun testBuild_withQuantityCurrencySource() {
        // Prepare the data
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalStateException::class)
    fun testBuild_withQuantityCurrencySourceDestination() {
        // Prepare the data
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)
        transactionBuilder.setDestination(DESTINATION)

        // Invoke the method
        transactionBuilder.build()

        // Check the result
    }

    @Test
    fun testBuild_Complete_WithoutSmsId() {
        // Prepare the data
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)
        transactionBuilder.setDestination(DESTINATION)
        transactionBuilder.setDate(DATE)
        val transactionExpected = Transaction(Transaction.DEFAULT_SMS_ID, QUANTITY, CURRENCY_AED, SOURCE_ADCB,
                DESTINATION, DATE)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
        assertEquals(transactionExpected, transactionCreated)
    }

    @Test
    fun testBuild_Complete() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)
        transactionBuilder.setDestination(DESTINATION)
        transactionBuilder.setDate(DATE)
        val transactionExpected = Transaction(SMS_ID, QUANTITY, CURRENCY_AED, SOURCE_ADCB,
                DESTINATION, DATE)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
        assertEquals(transactionExpected, transactionCreated)
    }

}