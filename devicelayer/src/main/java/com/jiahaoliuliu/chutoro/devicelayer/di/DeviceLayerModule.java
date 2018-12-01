package com.jiahaoliuliu.chutoro.devicelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.TransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ADCBSmsParserParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ISmsParserParametersFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceLayerModule {

    private static final String QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB = "ADCBSmsParserParametersFactory";
    private static final String QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB = "ADCBTransactionsProvider";

    @Provides
    SmsParserHelper provideSmsParserHelper() {
        return new SmsParserHelper();
    }

    @Provides
    @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB)
    ISmsParserParametersFactory provideADCBSmsParserParametersFactory() {
        return new ADCBSmsParserParametersFactory();
    }

    @Provides
    @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB)
    TransactionsProvider provideADCBTransactionsProvider(
            Context context, SmsParserHelper smsParserHelper,
            @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB)
            ISmsParserParametersFactory smsParserParametersFactory) {
        return new TransactionsProvider(context, smsParserHelper, smsParserParametersFactory);
    }

    @Provides
    CommonTransactionsProvider provideCommonTransactionsProvider(
            @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB)TransactionsProvider adcbTransactionsProvider) {
        return new CommonTransactionsProvider(adcbTransactionsProvider);
    }
}
