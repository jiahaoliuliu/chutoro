package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.Sms;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.entity.Transaction;
import com.jiahaoliuliu.chutoro.entity.TransactionBuilder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is a test against the sms patterns in the factory. More than a unit test
 * this is the core system test test
 */
@RunWith(MockitoJUnitRunner.class)
public class CitibankSmsParametersFactoryTest {

    private static final String SOURCE = "Citibank";

    // Pattern 1
    private static final long PATTERN_1_SMS_ID = 6543;
    private static final int PATTERN_1_SMS_QUANTITY = 28400;
    private static final long PATTERN_1_SMS_DATE = 1554595200000l;
    private static final String PATTERN_1_SMS_CURRENCY = "AED";
    private static final String PATTERN_1_SMS_DESTINATION = "DEIRA SHOWROOM DX";
    private static final String PATTERN_1_SMS_BODY = "You spent AED 284.00 at DEIRA SHOWROOM DX on 07/04/2019, on your Citi Card ending with 6446. The available spending limit is AED 39221.44.";

    // Pattern 1 (Other currency)
    private static final long PATTERN_1_CURRENCY_SMS_ID = 6543;
    private static final int PATTERN_1_CURRENCY_SMS_QUANTITY = 28400;
    private static final long PATTERN_1_CURRENCY_SMS_DATE = 1554595200000l;
    private static final String PATTERN_1_CURRENCY_SMS_CURRENCY = "USD";
    private static final String PATTERN_1_CURRENCY_SMS_DESTINATION = "DEIRA SHOWROOM DX";
    private static final String PATTERN_1_CURRENCY_SMS_BODY = "You spent USD 284.00 at DEIRA SHOWROOM DX on 07/04/2019, on your Citi Card ending with 6446. The available spending limit is AED 39221.44.";

    // Pattern 2
    private static final long PATTERN_2_SMS_ID = 6543;
    private static final int PATTERN_2_SMS_QUANTITY = 10185;
    private static final long PATTERN_2_SMS_DATE = 1554768000000l;
    private static final String PATTERN_2_SMS_CURRENCY = "AED";
    private static final String PATTERN_2_SMS_DESTINATION = "CAREFOUR MARKET        DU";
    private static final String PATTERN_2_SMS_BODY = "Your supplementary Citi Card ending with 2678 spent AED 101.85 at CAREFOUR MARKET        DU on 09/04/2019. The available limit is AED 77.04.";

    // Pattern 3
    private static final long PATTERN_3_SMS_ID = 6543;
    private static final int PATTERN_3_SMS_QUANTITY = 20000;
    private static final long PATTERN_3_SMS_DATE = 1543423200000l;
    private static final String PATTERN_3_SMS_CURRENCY = "AED";
    private static final String PATTERN_3_SMS_DESTINATION = "OTHER BANK ATM WITHDRAWAL";
    private static final String PATTERN_3_SMS_BODY = "Dear Customer, an amount of  AED 200.00 has been debited from your account **9135 Description: OTHER BANK ATM WITHDRAWAL   Balance:  AED 8,657.65";

    private CitibankSmsParametersFactory citibankSmsParametersFactory;
    private SmsParserHelper smsParserHelper;

    @Before
    public void setup() {
        citibankSmsParametersFactory = new CitibankSmsParametersFactory();
        smsParserHelper = new SmsParserHelper();
    }

    @Test
    public void testPattern1() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_1_SMS_ID, PATTERN_1_SMS_BODY, PATTERN_1_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        citibankSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_1_SMS_ID)
                        .setQuantity(PATTERN_1_SMS_QUANTITY)
                        .setCurrency(PATTERN_1_SMS_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_1_SMS_DESTINATION)
                        .setDate(PATTERN_1_SMS_DATE)
                        .build();
        assertEquals(rightTransaction, transaction);
    }

    @Test
    public void testPattern1_OtherCurrency() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_1_CURRENCY_SMS_ID, PATTERN_1_CURRENCY_SMS_BODY,
                PATTERN_1_CURRENCY_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        citibankSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_1_CURRENCY_SMS_ID)
                        .setQuantity(PATTERN_1_CURRENCY_SMS_QUANTITY)
                        .setCurrency(PATTERN_1_CURRENCY_SMS_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_1_CURRENCY_SMS_DESTINATION)
                        .setDate(PATTERN_1_CURRENCY_SMS_DATE)
                        .build();

        assertEquals(rightTransaction, transaction);
    }

    @Test
    public void testPattern2() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_2_SMS_ID, PATTERN_2_SMS_BODY, PATTERN_2_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        citibankSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_2_SMS_ID)
                        .setQuantity(PATTERN_2_SMS_QUANTITY)
                        .setCurrency(PATTERN_2_SMS_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_2_SMS_DESTINATION)
                        .setDate(PATTERN_2_SMS_DATE)
                        .build();

        assertEquals(rightTransaction, transaction);
    }

    @Test
    public void testPattern3() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_3_SMS_ID, PATTERN_3_SMS_BODY, PATTERN_3_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        citibankSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_3_SMS_ID)
                        .setQuantity(PATTERN_3_SMS_QUANTITY)
                        .setCurrency(PATTERN_3_SMS_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_3_SMS_DESTINATION)
                        .setDate(PATTERN_3_SMS_DATE)
                        .build();

        assertEquals(rightTransaction, transaction);
    }
}
