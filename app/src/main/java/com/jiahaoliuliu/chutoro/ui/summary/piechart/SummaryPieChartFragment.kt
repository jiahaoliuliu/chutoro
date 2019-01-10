package com.jiahaoliuliu.chutoro.ui.summary.piechart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jiahaoliuliu.chutoro.R
import com.jiahaoliuliu.chutoro.ui.MainApplication
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.view.PieChartView
import javax.inject.Inject

class SummaryPieChartFragment : Fragment(), SummaryPieChartContract.View {

    @Inject
    lateinit var presenter: SummaryPieChartContract.Presenter

    private lateinit var pieChartView: PieChartView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.getMainComponent().inject(this)
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_pie_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pieChartView = view.findViewById(R.id.pieChart)
        presenter.retrievePieChartData()
    }

    override fun showPieChart(pieChartData: PieChartData) {
        pieChartData.setHasCenterCircle(true)
        pieChartData.setHasLabels(true)
        pieChartData.valueLabelTextSize = 14
        pieChartView.pieChartData = pieChartData
    }
}
