package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Arrays;
import java.util.List;

public class HSBCSmsParametersFactory implements ISmsParametersFactory {

    private static final String SENDER = "HSBC";
    private static final String SOURCE = "HSBC";

    // Pattern 1
    // Example: Your Credit Card ending with ***1880 has been used for AED 5.73 on 09/12/2018 at SPINNEYS DUBAI LLC. Your available limit is AED 9414.74
    private static final String PATTERN_1 = "Your Credit Card ending with (.*?) has been used for (.*?) on (.*?) at (.*?)\\.";
    private static final int PATTERN_1_POSITION_QUANTITY = 2;
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
