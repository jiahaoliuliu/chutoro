package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import com.jiahaoliuliu.chutoro.entity.Source;

import java.util.Arrays;
import java.util.List;

public class EmirtesNBDParametersFactory implements ISmsParametersFactory {

    private static final String SENDER = "EmiratesNBD";
    private static final String SOURCE = Source.EmiratesNBD.toString();

    // Pattern 1
    // Example:
    // Purchase of AED 26.75 with Debit Card ending 6819 at ZOOM SITE 6562 BURJ AL SA, DUBAI. Avl Balance is AED 54,520.42.
    private static final String PATTERN_1 = "Purchase of (.*?) with Debit Card ending (.*?) at (.*?). \\.";
    private static final int PATTERN_1_POSITION_QUANTITY = 1;
    private static final int PATTERN_1_POSITION_DESTINATION = 3;
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
