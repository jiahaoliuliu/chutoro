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

public class NajmSmsParametersFactoryTest {

    private static final String SOURCE = "Najm";

    // Pattern 1
    private static final long PATTERN_1_SMS_ID = 6543;
    private static final int PATTERN_1_SMS_QUANTITY = 4900;
    private static final long PATTERN_1_SMS_DATE = 1543522832854l;
    private static final String PATTERN_1_SMS_CURRENCY = "AED";
    private static final String PATTERN_1_SMS_DESTINATION = "MCDONALDS -DXB AIRPORT AE";
    private static final String PATTERN_1_SMS_BODY = "You made a purchase of AED 49 at MCDONALDS -DXB AIRPORT AE  with your Najm Credit Card xxxxxxxxxxxx2933. Avl Limit AED 45117.";

    // Pattern 1 (Other currency)
    private static final long PATTERN_1_CURRENCY_SMS_ID = 6543;
    private static final int PATTERN_1_CURRENCY_SMS_QUANTITY = 2500;
    private static final long PATTERN_1_CURRENCY_SMS_DATE = 1542812469138l;
    private static final String PATTERN_1_CURRENCY_SMS_CURRENCY = "EUR";
    private static final String PATTERN_1_CURRENCY_SMS_DESTINATION = "WIKIMEDIA EUR US";
    private static final String PATTERN_1_CURRENCY_SMS_BODY = "You made a purchase of EUR 25 at WIKIMEDIA EUR US  with your Najm Credit Card xxxxxxxxxxxx2933. Avl Limit AED 48209.62.";

    private NajmSmsParametersFactory najmSmsParametersFactory;
    private SmsParserHelper smsParserHelper;

    @Before
    public void setup() {
        najmSmsParametersFactory = new NajmSmsParametersFactory();
        smsParserHelper = new SmsParserHelper();
    }

    @Test
    public void testPattern1() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_1_SMS_ID, PATTERN_1_SMS_BODY, PATTERN_1_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        najmSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_1_SMS_ID)
                        .setOriginalSms(PATTERN_1_SMS_BODY)
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
                        najmSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_1_CURRENCY_SMS_ID)
                        .setOriginalSms(PATTERN_1_CURRENCY_SMS_BODY)
                        .setQuantity(PATTERN_1_CURRENCY_SMS_QUANTITY)
                        .setCurrency(PATTERN_1_CURRENCY_SMS_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_1_CURRENCY_SMS_DESTINATION)
                        .setDate(PATTERN_1_CURRENCY_SMS_DATE)
                        .build();

        assertEquals(rightTransaction, transaction);
    }
}
