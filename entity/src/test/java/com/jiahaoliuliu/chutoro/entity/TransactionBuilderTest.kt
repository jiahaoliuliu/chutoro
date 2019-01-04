package com.jiahaoliuliu.chutoro.entity

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException
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

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongSms() {
        // Prepare the data
        val wrongSmsId = -11L
        transactionBuilder.setSmsId(wrongSmsId)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongQuantityAsString() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        val wrongQuantity = "wrongQuantity"
        transactionBuilder.setQuantity(wrongQuantity)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test
    fun testBuild_SetQuantityAsFloat() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        val quantityFloat = QUANTITY / 100f
        transactionBuilder.setQuantity(quantityFloat)
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

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongQuantityAsInt() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        val wrongQuantity = -1
        transactionBuilder.setQuantity(wrongQuantity)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetEmptyCurrency() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency("")

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongCurrency() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        val wrongCurrency = "adabsdf"
        transactionBuilder.setCurrency(wrongCurrency)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetEmptySource() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource("")

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongSource() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        val wrongSource = "asdfadsf"
        transactionBuilder.setSource(wrongSource)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetEmptyDestination() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)
        transactionBuilder.setDestination("")

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }

    @Test(expected = IllegalArgumentException::class)
    fun testBuild_SetWrongDate() {
        // Prepare the data
        transactionBuilder.setSmsId(SMS_ID)
        transactionBuilder.setQuantity(QUANTITY)
        transactionBuilder.setCurrency(CURRENCY_AED)
        transactionBuilder.setSource(SOURCE_ADCB)
        transactionBuilder.setDestination(DESTINATION)
        val wrongDate = -1L
        transactionBuilder.setDate(wrongDate)

        // Invoke the method
        val transactionCreated = transactionBuilder.build()

        // Check the result
    }
}