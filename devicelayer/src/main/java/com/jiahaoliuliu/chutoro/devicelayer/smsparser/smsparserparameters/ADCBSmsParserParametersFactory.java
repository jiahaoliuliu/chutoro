package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Arrays;
import java.util.List;

public class ADCBSmsParserParametersFactory implements ISmsParserParametersFactory {

    private static final String SOURCE = "ADCB";

    // Pattern 1
    // Example Your credit card XXX4921 was used for USD15.00 on 20/11/2018 14:31:31 at PREY, INC.,SAN FRANCISCO-US. Available credit limit is now AED39109.37.
    private static final String PATTERN_1 = "Your credit card (.*?) was used for AED(.*?) on (.*?) at (.*?)\\. ";
    private static final String PATTERN_1_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final int PATTERN_1_POSITION_QUANTITY = 2;
    private static final int PATTERN_1_POSITION_DESTINATION = 4;
    private static final int PATTERN_1_POSITION_DATE = 3;

    // Pattern 2
    // Example: AED1200.00 withdrawn from acc. XXX132001 on Nov 16 2018  9:40AM at ATM-MARINA MALL DXB. Avl.Bal.AED29349.34. Be cautious with large amt. of cash.
    private static final String PATTERN_2 = "AED(.*?) withdrawn from acc.(.*?) on (.*?) at (.*?)\\.";
    private static final String PATTERN_2_DATE_FORMAT = "MMM dd yyyy  hh:mmaa";
    private static final int PATTERN_2_POSITION_QUANTITY = 1;
    private static final int PATTERN_2_POSITION_DESTINATION = 4;
    private static final int PATTERN_2_POSITION_DATE = 3;

    @Override
    public List<SmsParserParameters> createSmsParserParametersList() {
        SmsParserParameters smsParserParameters1 = new SmsParserParameters(
                PATTERN_1, PATTERN_1_DATE_FORMAT, PATTERN_1_POSITION_QUANTITY,
                PATTERN_1_POSITION_DESTINATION, PATTERN_1_POSITION_DATE, SOURCE);

        SmsParserParameters smsParserParameters2 = new SmsParserParameters(
                PATTERN_2, PATTERN_2_DATE_FORMAT, PATTERN_2_POSITION_QUANTITY,
                PATTERN_2_POSITION_DESTINATION, PATTERN_2_POSITION_DATE, SOURCE);

        return Arrays.asList(smsParserParameters1, smsParserParameters2);
    }
}
