package com.jiahaoliuliu.chutoro.ui.summary.piechart

import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.util.ChartUtils

class SummaryPieChartPresenter: SummaryPieChartContract.Presenter {

    private lateinit var view: SummaryPieChartContract.View

    override fun setView(view: SummaryPieChartContract.View) {
        this.view = view
    }

    override fun retrievePieChartData() {
        var pieDataContent : MutableList<SliceValue> = mutableListOf()

        pieDataContent.add(SliceValue(100f, ChartUtils.COLOR_BLUE).setLabel("Q1: $10"))
        pieDataContent.add(SliceValue(25f, ChartUtils.COLOR_GREEN).setLabel("Q2: $4"))
        pieDataContent.add(SliceValue(10f, ChartUtils.COLOR_RED).setLabel("Q3: $18"))
        pieDataContent.add(SliceValue(60f, ChartUtils.COLOR_ORANGE).setLabel("Q4, $28"))

        val pieChartData = PieChartData(pieDataContent)
        view.showPieChart(pieChartData)
    }
}