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
public class ADCBSmsParametersFactoryTest {

    private static final String SOURCE = "ADCB";

    // Pattern 1
    private static final long PATTERN_1_SMS_ID = 6543;
    private static final int PATTERN_1_SMS_QUANTITY = 359;
    private static final long PATTERN_1_SMS_DATE = 1542443169000l;
    private static final String PATTERN_1_SMS_CURRENCY = "AED";
    private static final String PATTERN_1_SMS_DESTINATION = "GOOGLE*IDREAMS,G.CO HELPPAY#-US";
    private static final String PATTERN_1_SMS_BODY = "Your credit card XXX4921 was used for AED3.59 on 17/11/2018 12:26:09 at "
            + PATTERN_1_SMS_DESTINATION + ". Available credit limit is now AED9192.92.";

    // Pattern 1 (Other currency)
    private static final long PATTERN_1_CURRENCY_SMS_ID = 6543;
    private static final int PATTERN_1_CURRENCY_SMS_QUANTITY = 4826;
    private static final long PATTERN_1_CURRENCY_SMS_DATE = 1543260127000l;
    private static final String PATTERN_1_CURRENCY_SMS_CURRENCY = "USD";
    private static final String PATTERN_1_CURRENCY_SMS_DESTINATION = "LUCIDCHART COM CHARG,SOUTH JORDAN-US";
    private static final String PATTERN_1_CURRENCY_SMS_BODY = "Your credit card XXX4921 was used for USD48.26 on 26/11/2018 23:22:07 at LUCIDCHART COM CHARG,SOUTH JORDAN-US. Available credit limit is now AED38792.72.";

    // Pattern 2
    private static final long PATTERN_2_SMS_ID = 6543;
    private static final int PATTERN_2_SMS_QUANTITY = 120000;
    private static final long PATTERN_2_SMS_DATE = 1542346800000l;
    private static final String PATTERN_2_SMS_CURRENCY = "AED";
    private static final String PATTERN_2_SMS_DESTINATION = "ATM-MARINA MALL DXB";
    private static final String PATTERN_2_SMS_BODY = "AED1200.00 withdrawn from acc. XXX132001 on Nov 16 2018  9:40AM at ATM-MARINA MALL DXB. Avl.Bal.AED29349.34. Be cautious with large amt. of cash.";

    private static final long PATTERN_2_SMS_2_ID = 6543;
    private static final int PATTERN_2_SMS_2_QUANTITY = 800000;
    private static final long PATTERN_2_SMS_2_DATE = 1543423200000l;
    private static final String PATTERN_2_SMS_2_CURRENCY = "AED";
    private static final String PATTERN_2_SMS_2_DESTINATION = "ATM-RTAMOEDXB";
    private static final String PATTERN_2_SMS_2_BODY = "AED8000.00 withdrawn from acc. XXX132001 on Nov 28 2018  8:40PM at ATM-RTAMOEDXB. Avl.Bal.AED21348.34. Be cautious with large amt. of cash.";

    private ADCBSmsParametersFactory adcbSmsParametersFactory;
    private SmsParserHelper smsParserHelper;

    @Before
    public void setup() {
        adcbSmsParametersFactory = new ADCBSmsParametersFactory();
        smsParserHelper = new SmsParserHelper();
    }

    @Test
    public void testPattern1() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_1_SMS_ID, PATTERN_1_SMS_BODY, PATTERN_1_SMS_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        adcbSmsParametersFactory.createSmsParserParametersList());

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
                        adcbSmsParametersFactory.createSmsParserParametersList());

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
                        adcbSmsParametersFactory.createSmsParserParametersList());

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
    public void testPattern2_Sms2() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_2_SMS_2_ID, PATTERN_2_SMS_2_BODY, PATTERN_2_SMS_2_DATE);

        // Execute the method
        Transaction transaction =
                smsParserHelper.parseSmsToTransaction(sms,
                        adcbSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertNotNull(transaction);
        Transaction rightTransaction =
                new TransactionBuilder()
                        .setSmsId(PATTERN_2_SMS_2_ID)
                        .setQuantity(PATTERN_2_SMS_2_QUANTITY)
                        .setCurrency(PATTERN_2_SMS_2_CURRENCY)
                        .setSource(SOURCE)
                        .setDestination(PATTERN_2_SMS_2_DESTINATION)
                        .setDate(PATTERN_2_SMS_2_DATE)
                        .build();

        assertEquals(rightTransaction, transaction);
    }
}
