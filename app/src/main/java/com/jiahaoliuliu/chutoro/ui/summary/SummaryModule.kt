package com.jiahaoliuliu.chutoro.ui.summary

import com.jiahaoliuliu.chutoro.ui.summary.piechart.SummaryPieChartContract
import com.jiahaoliuliu.chutoro.ui.summary.piechart.SummaryPieChartPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SummaryModule {

    @Provides
    @Singleton
    fun provideSummaryPieChartPresenter(): SummaryPieChartContract.Presenter {
        return SummaryPieChartPresenter()
    }
}