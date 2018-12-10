package com.jiahaoliuliu.chutoro.ui.addtransaction

import com.jiahaoliuliu.chutoro.usecase.AddTransactionUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddTransactionModule {

    @Provides
    @Singleton
    internal fun providePresenter(addTransactionUseCase: AddTransactionUseCase): AddTransactionContract.Presenter {
        return AddTransactionPresenter(addTransactionUseCase)
    }
}