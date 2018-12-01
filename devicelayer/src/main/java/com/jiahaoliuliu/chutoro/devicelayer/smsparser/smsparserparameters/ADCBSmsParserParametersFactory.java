package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Arrays;
import java.util.List;

public class ADCBSmsParserParametersFactory implements ISmsParserParametersFactory {

    private static final String SOURCE = "ADCB";

    // Pattern parameters
    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String PATTERN_1_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int PATTERN_1_POSITION_QUANTITY = 2;
    private static final int PATTERN_1_POSITION_DESTINATION = 4;
    private static final int PATTERN_1_POSITION_DATE = 3;

    @Override
    public List<SmsParserParameters> createSmsParserParametersList() {
        SmsParserParameters smsParserParameters1 = new SmsParserParameters(
                PATTERN_1, PATTERN_1_DATE_FORMAT, PATTERN_1_POSITION_QUANTITY,
                PATTERN_1_POSITION_DESTINATION, PATTERN_1_POSITION_DATE, SOURCE);

        return Arrays.asList(smsParserParameters1);
    }
}
