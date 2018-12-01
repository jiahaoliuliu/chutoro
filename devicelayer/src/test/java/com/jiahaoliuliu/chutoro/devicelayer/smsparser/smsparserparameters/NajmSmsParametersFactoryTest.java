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

public class NajmSmsParametersFactoryTest {

    private static final String SOURCE = "Najm";

    // Pattern 1
    private static final long PATTERN_1_SMS_ID = 6543;
    private static final int PATTERN_1_SMS_QUANTITY = 49;
    private static final long PATTERN_1_SMS_DATE = 1543522832854l;
    private static final String PATTERN_1_SMS_DESTINATION = "MCDONALDS -DXB AIRPORT AE";
    private static final String PATTERN_1_SMS_BODY = "You made a purchase of AED 49 at MCDONALDS -DXB AIRPORT AE  with your Najm Credit Card xxxxxxxxxxxx2933. Avl Limit AED 45117.";

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
        List<Transaction> transactionList =
                smsParserHelper.mapSmsListToTransactionsList(
                        Arrays.asList(sms),
                        najmSmsParametersFactory.createSmsParserParametersList());

        // Verify the results
        assertEquals(1, transactionList.size());
        Transaction rightTransaction = new Transaction(PATTERN_1_SMS_ID, PATTERN_1_SMS_QUANTITY,
                SOURCE, PATTERN_1_SMS_DESTINATION, PATTERN_1_SMS_DATE);
        assertEquals(rightTransaction, transactionList.get(0));
    }
}
