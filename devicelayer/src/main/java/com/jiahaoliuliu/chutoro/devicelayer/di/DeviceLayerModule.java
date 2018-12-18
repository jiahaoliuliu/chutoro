package com.jiahaoliuliu.chutoro.devicelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.TransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ADCBSmsParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.EmirtesNBDParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.HSBCSmsParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.ISmsParametersFactory;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.smsparserparameters.NajmSmsParametersFactory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceLayerModule {

    // ADCB
    private static final String QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB = "ADCBSmsParametersFactory";
    private static final String QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB = "ADCBTransactionsProvider";

    @Provides
    @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB)
    ISmsParametersFactory provideADCBSmsParserParametersFactory() {
        return new ADCBSmsParametersFactory();
    }

    @Provides
    @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB)
    TransactionsProvider provideADCBTransactionsProvider(
            Context context, SmsParserHelper smsParserHelper,
            @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_ADCB)
                    ISmsParametersFactory smsParserParametersFactory) {
        return new TransactionsProvider(context, smsParserHelper, smsParserParametersFactory);
    }

    // Najm
    private static final String QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_NAJM = "NajmSmsParserParametersFactory";
    private static final String QUALIFIED_NAME_TRANSACTIONS_PROVIDER_NAJM = "NajmTransactionsProvider";

    @Provides
    @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_NAJM)
    ISmsParametersFactory provideNajmSmsParserParametersFactory() {
        return new NajmSmsParametersFactory();
    }

    @Provides
    @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_NAJM)
    TransactionsProvider provideNajmTransactionsProvider(
            Context context, SmsParserHelper smsParserHelper,
            @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_NAJM)
                    ISmsParametersFactory smsParserParametersFactory) {
        return new TransactionsProvider(context, smsParserHelper, smsParserParametersFactory);
    }

    // HSBC
    private static final String QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_HSBC = "HSBCSmsParserParametersFactory";
    private static final String QUALIFIED_NAME_TRANSACTIONS_PROVIDER_HSBC = "HSBCTransactionsProvider";

    @Provides
    @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_HSBC)
    ISmsParametersFactory provideHSBCSmsParserParametersFactory() {
        return new HSBCSmsParametersFactory();
    }

    @Provides
    @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_HSBC)
    TransactionsProvider provideHSBCTransactionsProvider(
            Context context, SmsParserHelper smsParserHelper,
            @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_HSBC)
                    ISmsParametersFactory smsParserParametersFactory) {
        return new TransactionsProvider(context, smsParserHelper, smsParserParametersFactory);
    }

    // Emirtes NBD
    private static final String QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_EMIRATES_NBD = "EmiratesNBDSmsParserParametersFactory";
    private static final String QUALIFIED_NAME_TRANSACTIONS_PROVIDER_EMIRATES_NBD = "EmiratesNBDTransactionsProvider";

    @Provides
    @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_EMIRATES_NBD)
    ISmsParametersFactory provideEmiratesNBDSmsParserParametersFactory() {
        return new EmirtesNBDParametersFactory();
    }

    @Provides
    @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_EMIRATES_NBD)
    TransactionsProvider provideEmiratesNBDTransactionsProvider(
            Context context, SmsParserHelper smsParserHelper,
            @Named(QUALIFIED_NAME_SMS_PARSER_PARAMETERS_FACTORY_EMIRATES_NBD)
                    ISmsParametersFactory smsParserParametersFactory) {
        return new TransactionsProvider(context, smsParserHelper, smsParserParametersFactory);
    }


    // General
    @Provides
    SmsParserHelper provideSmsParserHelper() {
        return new SmsParserHelper();
    }

    @Provides
    CommonTransactionsProvider provideCommonTransactionsProvider(
            @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_ADCB)TransactionsProvider adcbTransactionsProvider,
            @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_NAJM)TransactionsProvider najmTransactionsProvider,
            @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_HSBC)TransactionsProvider hsbcTransactionsProvider,
            @Named(QUALIFIED_NAME_TRANSACTIONS_PROVIDER_EMIRATES_NBD)TransactionsProvider emiratesNBDTransactionsProvider) {
        return new CommonTransactionsProvider(adcbTransactionsProvider, najmTransactionsProvider,
                hsbcTransactionsProvider, emiratesNBDTransactionsProvider);
    }
}
