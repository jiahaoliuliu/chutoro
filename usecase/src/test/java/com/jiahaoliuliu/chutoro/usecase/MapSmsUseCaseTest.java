package com.jiahaoliuliu.chutoro.usecase;

import com.jiahaoliuliu.chutoro.entity.Sms;
import com.jiahaoliuliu.chutoro.entity.SmsParsingParameters;
import com.jiahaoliuliu.chutoro.entity.Transaction;
import com.jiahaoliuliu.usecase.MapSmsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MapSmsUseCaseTest {

    private static final int SMS_1_QUANTITY = 359;
    private static final long SMS_1_DATE = 1542443169000l;
    private static final String SMS_1_DESTINATION = "GOOGLE*IDREAMS,G.CO HELPPAY#-US";
    private static final String SMS_1_BODY = "Your credit card XXX4921 was used for AED3.59 on 17/11/2018 12:26:09 at "
            + SMS_1_DESTINATION + ". Available credit limit is now AED39192.92.";

    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int POSITION_QUANTITY = 2;
    private static final int POSITION_DESTINATION = 4;
    private static final int POSITION_DATE = 3;
    private static final String SOURCE = "ADCB";

    private MapSmsUseCase mapSmsUseCase;

    @Before
    public void setup() {
        mapSmsUseCase = new MapSmsUseCase();
    }

    @Test
    public void mapSmsListToTransactionsList_Success() {
        // Prepare the data
        Sms sms = new Sms(SMS_1_BODY, SMS_1_DATE);
        List<Sms> smsList = new ArrayList();
        smsList.add(sms);
        Transaction rightTransaction = new Transaction(SMS_1_QUANTITY, SOURCE, SMS_1_DESTINATION, SMS_1_DATE);

        SmsParsingParameters smsParsingParameters = new SmsParsingParameters(PATTERN_1, DATE_FORMAT,
                POSITION_QUANTITY, POSITION_DESTINATION, POSITION_DATE, SOURCE);

        // Execute the method
        List<Transaction> transactionList = mapSmsUseCase.mapSmsListToTransactionsList(smsList, smsParsingParameters);

        // Verify the results
        assertEquals(1, transactionList.size());
        assertEquals(rightTransaction, transactionList.get(0));
    }
}
