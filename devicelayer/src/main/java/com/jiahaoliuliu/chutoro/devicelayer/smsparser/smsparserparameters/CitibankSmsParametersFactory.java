package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Arrays;
import java.util.List;

class CitibankSmsParametersFactory implements ISmsParametersFactory {
    private static final String SENDER = "Citibank";
    private static final String SOURCE = "Citibank";

    // Pattern 1
    // Example: You spent AED 284.00 at DEIRA SHOWROOM DX on 07/04/2019, on your Citi Card ending with 6446. The available spending limit is AED 39221.44.
    private static final String PATTERN_1 = "You spent (.*?) at (.*?) on (.*?), on your Citi Card ending with (.*?). The available spending limit is (.*?).";
    private static final int PATTERN_1_POSITION_QUANTITY = 1;
    private static final String PATTERN_1_DATE_FORMAT = "dd/MM/yyyy";
    private static final int PATTERN_1_POSITION_DATE = 3;
    private static final int PATTERN_1_POSITION_DESTINATION = 2;

    // Pattern 2
    // Example: Your supplementary Citi Card ending with 2678 spent AED 101.85 at CAREFOUR MARKET DU on 09/04/2019. The available limit is AED 77.04.
    private static final String PATTERN_2 = "our supplementary Citi Card ending with (.*?) spent (.*?) at (.*?) on (.*?). The available limit is (.*?)."
    private static final String PATTERN_2_DATE_FORMAT = PATTERN_1_DATE_FORMAT;
    private static final int PATTERN_2_POSITION_QUANTITY = 2;
    private static final int PATTERN_2_POSITION_DATE = 4;
    private static final int PATTERN_2_POSITION_DESTINATION = 3;

    // Pattern 3
    // Example: Dear Customer, an amount of  AED 200.00 has been debited from your account **9135 Description: OTHER BANK ATM WITHDRAWAL Balance:  AED 8,657.65
    private static final String PATTERN_3 = "Dear Customer, an amount of  (.*?) has been debited from your account (.*?) Description: (.*?) Balance:  (.*?)";
    private static final int PATTERN_3_POSITION_QUANTITY = 1;
    private static final int PATTERN_3_POSITION_DESTINATION = 3;
    // The date is not shown on the sms. In this case, the received date of the sms will be used
    private static final int PATTERN_3_POSITION_DATE = -1;
    private static final String PATTERN_3_DATE_FORMAT = "";

    @Override
    public String getSmsSender() { return SENDER }

    @Override
    public List<SmsParserParameters> createSmsParserParametersList() {
        SmsParserParameters smsParserParameters1 = new SmsParserParameters(
                PATTERN_1, PATTERN_1_DATE_FORMAT, PATTERN_1_POSITION_QUANTITY,
                PATTERN_1_POSITION_DESTINATION, PATTERN_1_POSITION_DATE, SOURCE);

        SmsParserParameters smsParserParameters2 = new SmsParserParameters(
                PATTERN_2, PATTERN_2_DATE_FORMAT, PATTERN_2_POSITION_QUANTITY,
                PATTERN_2_POSITION_DESTINATION, PATTERN_2_POSITION_DATE, SOURCE);

        SmsParserParameters smsParserParameters3 = new SmsParserParameters(
                PATTERN_3, PATTERN_3_DATE_FORMAT, PATTERN_3_POSITION_QUANTITY,
                PATTERN_3_POSITION_DESTINATION, PATTERN_3_POSITION_DATE, SOURCE);

        return Arrays.asList(smsParserParameters1, smsParserParameters2, smsParserParameters3);
    }
}