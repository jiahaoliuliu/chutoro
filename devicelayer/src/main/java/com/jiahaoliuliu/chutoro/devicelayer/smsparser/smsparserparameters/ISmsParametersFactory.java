package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.List;

public interface ISmsParametersFactory {

    /**
     * Get the sender name in the SMS in order to query it
     * @return
     */
    String getSmsSender();

    /**
     * Create a list of Sms parser parameters
     * @return
     */
    List<SmsParserParameters> createSmsParserParametersList();
}
