package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Arrays;
import java.util.List;

public class NajmSmsParametersFactory implements ISmsParametersFactory {

    private static final String SENDER = "Najm";
    private static final String SOURCE = "Najm";

    // Pattern 1
    // Example You made a purchase of AED 49 at MCDONALDS -DXB AIRPORT AE  with your Najm Credit Card xxxxxxxxxxxx2933. Avl Limit AED 45117.
    private static final String PATTERN_1 = "You made a purchase of AED (.*?) at (.*?)  with your (.*?)\\.";
    private static final int PATTERN_1_POSITION_QUANTITY = 1;
    private static final int PATTERN_1_POSITION_DESTINATION = 2;
    // The date is not shown on the sms. In this case, the received date of the sms will be used
    private static final int PATTERN_1_POSITION_DATE = -1;
    private static final String PATTERN_1_DATE_FORMAT = "";

    @Override
    public String getSmsSender() {
        return SENDER;
    }

    @Override
    public List<SmsParserParameters> createSmsParserParametersList() {
        SmsParserParameters smsParserParameters1 = new SmsParserParameters(
                PATTERN_1, PATTERN_1_DATE_FORMAT, PATTERN_1_POSITION_QUANTITY,
                PATTERN_1_POSITION_DESTINATION, PATTERN_1_POSITION_DATE, SOURCE);

        return Arrays.asList(smsParserParameters1);
    }
}
