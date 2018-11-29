package com.jiahaoliuliu.chutoro.devicelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.devicelayer.ADCBTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.ITransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.smsparser.SmsParserHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceLayerModule {

    @Provides
    SmsParserHelper provideSmsParserHelper() {
        return new SmsParserHelper();
    }

    @Provides
    ITransactionsProvider provideADCBTransactionsProvider(Context context, SmsParserHelper smsParserHelper) {
        return new ADCBTransactionsProvider(context, smsParserHelper);
    }

    @Provides
    CommonTransactionsProvider provideCommonTransactionsProvider(ITransactionsProvider
        adcbTransactionsProvider) {
        return new CommonTransactionsProvider(adcbTransactionsProvider);
    }
}
