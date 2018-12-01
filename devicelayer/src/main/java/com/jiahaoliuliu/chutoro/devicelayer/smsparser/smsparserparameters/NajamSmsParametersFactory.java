package com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters;

import java.util.Collections;
import java.util.List;

public class NajamSmsParametersFactory implements ISmsParametersFactory {

    private static final String SENDER = "Najm";

    @Override
    public String getSmsSender() {
        return SENDER;
    }

    @Override
    public List<SmsParserParameters> createSmsParserParametersList() {
        return Collections.emptyList();
    }
}
