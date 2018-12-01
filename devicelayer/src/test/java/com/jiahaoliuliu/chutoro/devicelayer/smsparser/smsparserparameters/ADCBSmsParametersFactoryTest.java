package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.Sms;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    private static final String PATTERN_1_SMS_DESTINATION = "GOOGLE*IDREAMS,G.CO HELPPAY#-US";
    private static final String PATTERN_1_SMS_BODY = "Your credit card XXX4921 was used for AED3.59 on 17/11/2018 12:26:09 at "
            + PATTERN_1_SMS_DESTINATION + ". Available credit limit is now AED9192.92.";

    // Pattern 2
    private static final long PATTERN_2_SMS_ID = 6543;
    private static final int PATTERN_2_SMS_QUANTITY = 120000;
    private static final long PATTERN_2_SMS_DATE = 1542346800000l;
    private static final String PATTERN_2_SMS_DESTINATION = "ATM-MARINA MALL DXB";
    private static final String PATTERN_2_SMS_BODY = "AED1200.00 withdrawn from acc. XXX132001 on Nov 16 2018  9:40AM at ATM-MARINA MALL DXB. Avl.Bal.AED29349.34. Be cautious with large amt. of cash.";

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
        List<Transaction> transactionList =
                smsParserHelper.mapSmsListToTransactionsList(
                        Arrays.asList(sms),
                        adcbSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertEquals(1, transactionList.size());
        Transaction rightTransaction = new Transaction(PATTERN_1_SMS_ID, PATTERN_1_SMS_QUANTITY,
                SOURCE, PATTERN_1_SMS_DESTINATION, PATTERN_1_SMS_DATE);
        assertEquals(rightTransaction, transactionList.get(0));
    }

    @Test
    public void testPattern2() {
        // Prepare the data
        Sms sms = new Sms(PATTERN_2_SMS_ID, PATTERN_2_SMS_BODY, PATTERN_2_SMS_DATE);

        // Execute the method
        List<Transaction> transactionList =
                smsParserHelper.mapSmsListToTransactionsList(
                        Arrays.asList(sms),
                        adcbSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertEquals(1, transactionList.size());
        Transaction rightTransaction = new Transaction(PATTERN_2_SMS_ID, PATTERN_2_SMS_QUANTITY,
                SOURCE, PATTERN_2_SMS_DESTINATION, PATTERN_2_SMS_DATE);
        assertEquals(rightTransaction, transactionList.get(0));
    }
}
