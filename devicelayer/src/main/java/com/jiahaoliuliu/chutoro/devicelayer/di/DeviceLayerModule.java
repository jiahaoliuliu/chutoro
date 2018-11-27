package com.jiahaoliuliu.chutoro.devicelayer.di;

import android.content.Context;

import com.jiahaoliuliu.chutoro.devicelayer.ADCBTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.CommonTransactionsProvider;
import com.jiahaoliuliu.chutoro.devicelayer.ITransactionsProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DeviceLayerModule {

    @Provides
    ITransactionsProvider provideADCBTransactionsProvider(Context context) {
        return new ADCBTransactionsProvider(context);
    }

    @Provides
    CommonTransactionsProvider provideCommonTransactionsProvider(ITransactionsProvider
        adcbTransactionsProvider) {
        return new CommonTransactionsProvider(adcbTransactionsProvider);
    }
}
