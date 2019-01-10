package com.jiahaoliuliu.chutoro.ui.summary.piechart

import lecho.lib.hellocharts.model.PieChartData

interface SummaryPieChartContract {

    interface View {
        fun showPieChart(pieChartData: PieChartData)
    }

    interface Presenter {
        fun setView(view: View)

        fun retrievePieChartData()
    }
}