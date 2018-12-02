package com.jiahaoliuliu.chutoro.ui.addtransaction

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddTransactionModule {

    @Provides
    @Singleton
    internal fun providePresenter(): AddTransactionContract.Presenter {
        return AddTransactionPresenter()
    }
}