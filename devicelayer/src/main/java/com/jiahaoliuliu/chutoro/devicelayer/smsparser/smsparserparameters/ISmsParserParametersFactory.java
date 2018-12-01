package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.List;

public interface ISmsParserParametersFactory {

    /**
     * Create a list of Sms parser parameters
     * @return
     */
    List<SmsParserParameters> createSmsParserParametersList();
}
