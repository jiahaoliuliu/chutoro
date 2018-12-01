package com.jiahaoliuliu.chutoro.devicelayer.smsparser;

import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ISmsParserParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.SmsParserParameters;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SmsParserHelperTest {

    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";

    private static final long SMS_1_ID = 6543;
    private static final int SMS_1_QUANTITY = 359;
    private static final long SMS_1_DATE = 1542443169000l;
    private static final String SMS_1_DESTINATION = "GOOGLE*IDREAMS,G.CO HELPPAY#-US";
    private static final String SMS_1_BODY = "Your credit card XXX4921 was used for AED3.59 on 17/11/2018 12:26:09 at "
            + SMS_1_DESTINATION + ". Available credit limit is now AED9192.92.";

    private static final long SMS_2_ID = 6542;
    private static final long SMS_2_DATE = 1542443169000l;
    private static final String SMS_2_BODY = "Your credit card XXX4921 was used for Extra word AED3.59 on 17/11/2018 12:26:09 at "
            + SMS_1_DESTINATION + ". Available credit limit is now AED39192.92.";

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int POSITION_QUANTITY = 2;
    private static final int POSITION_QUANTITY_WRONG = POSITION_QUANTITY + 1;
    private static final int POSITION_DESTINATION = 4;
    private static final int POSITION_DESTINATION_WRONG = POSITION_DESTINATION + 1;
    private static final int POSITION_DATE = 3;
    private static final int POSITION_DATE_WRONG = POSITION_DATE + 1;

    private static final String SOURCE = "ADCB";

    private SmsParserHelper smsParserHelper;

    @Before
    public void setup() {
        smsParserHelper = new SmsParserHelper();
    }

    @Test
    public void mapSmsListToTransactionsList_Success() {
        // Prepare the data
        Sms sms = new Sms(SMS_1_ID, SMS_1_BODY, SMS_1_DATE);

        // Execute the method
        List<ITransaction> transactionList =
                smsParserHelper.mapSmsListToTransactionsList(
                        Arrays.asList(sms),
                        Arrays.asList(
                            createSmsParserParameters(
                                    PATTERN_1, DATE_FORMAT, POSITION_QUANTITY, POSITION_DESTINATION,
                                    POSITION_DATE, SOURCE)));

        // Verify the results
        assertEquals(1, transactionList.size());
        Transaction rightTransaction = new Transaction(SMS_1_ID, SMS_1_QUANTITY, SOURCE, SMS_1_DESTINATION, SMS_1_DATE);
        assertEquals(rightTransaction, transactionList.get(0));
    }

//    @Test
//    public void mapSmsListToTransactionsList_SmsListNull() {
//        // Prepare the data
//        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
//                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE, SOURCE);
//
//        // Execute the method
//        List<ITransaction> transactionList =
//                smsParserHelper.mapSmsListToTransactionsList(null, smsParserParameters);
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }
//
//    @Test
//    public void mapSmsListToTransactionsList_SmsParametersNull() {
//        // Prepare the data
//        Sms sms = new Sms(SMS_1_ID, SMS_1_BODY, SMS_1_DATE);
//        List<Sms> smsList = new ArrayList();
//        smsList.add(sms);
//
//        // Execute the method
//        List<ITransaction> transactionList = smsParserHelper.mapSmsListToTransactionsList(smsList, null);
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }
//
//    @Test
//    public void mapSmsListToTransactionsList_Failure() {
//        // Prepare the data
//        Sms sms = new Sms(SMS_2_ID, SMS_2_BODY, SMS_2_DATE);
//        List<Sms> smsList = new ArrayList();
//        smsList.add(sms);
//
//        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
//                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE, SOURCE);
//
//        // Execute the method
//        List<ITransaction> transactionList = smsParserHelper.mapSmsListToTransactionsList(smsList,
//                Arrays.asList(smsParserParameters));
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }
//
//    @Test
//    public void mapSmsListToTransactionsList_WrongPositionQuantity() {
//        // Prepare the data
//        Sms sms = new Sms(SMS_1_ID, SMS_1_BODY, SMS_1_DATE);
//        List<Sms> smsList = new ArrayList();
//        smsList.add(sms);
//
//        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
//                POSITION_QUANTITY_WRONG, POSITION_DESTINATION, POSITION_DATE, SOURCE);
//
//        // Execute the method
//        List<ITransaction> transactionList = smsParserHelper.mapSmsListToTransactionsList(smsList, smsParserParameters);
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }
//
//    @Test
//    public void mapSmsListToTransactionsList_WrongPositionDate() {
//        // Prepare the data
//        Sms sms = new Sms(SMS_1_ID, SMS_1_BODY, SMS_1_DATE);
//        List<Sms> smsList = new ArrayList();
//        smsList.add(sms);
//
//        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
//                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE_WRONG, SOURCE);
//
//        // Execute the method
//        List<ITransaction> transactionList = smsParserHelper.mapSmsListToTransactionsList(smsList, smsParserParameters);
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }
//
//    @Test
//    public void mapSmsListToTransactionsList_WrongPositionDestination() {
//        // Prepare the data
//        Sms sms = new Sms(SMS_1_ID, SMS_1_BODY, SMS_1_DATE);
//        List<Sms> smsList = new ArrayList();
//        smsList.add(sms);
//
//        SmsParserParameters smsParserParameters = new SmsParserParameters(PATTERN_1, DATE_FORMAT,
//                POSITION_QUANTITY, POSITION_DESTINATION_WRONG, POSITION_DATE, SOURCE);
//
//        // Execute the method
//        List<ITransaction> transactionList = smsParserHelper.mapSmsListToTransactionsList(smsList, smsParserParameters);
//
//        // Verify the results
//        assertEquals(0, transactionList.size());
//    }

    private SmsParserParameters createSmsParserParameters(
            String pattern, String dateFormat, int positionQuantity, int positionDestination,
            int positionDate, String source) {
        SmsParserParameters smsParserParameters = mock(SmsParserParameters.class);
        when(smsParserParameters.getPattern()).thenReturn(pattern);
        when(smsParserParameters.getDateFormat()).thenReturn(dateFormat);
        when(smsParserParameters.getPositionQuantity()).thenReturn(positionQuantity);
        when(smsParserParameters.getPositionDestination()).thenReturn(positionDestination);
        when(smsParserParameters.getPositionDate()).thenReturn(positionDate);
        when(smsParserParameters.getSource()).thenReturn(source);
        return smsParserParameters;
    }
}
