package com.falabella.challenge.ui.economicindicatordetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.domain.model.Serie
import com.falabella.challenge.R
import com.falabella.challenge.ui.common.BaseFragment
import com.falabella.challenge.ui.economicindicatordetail.economicindicatorserieitem.EconomicIndicatorSerieRecyclerViewAdapter
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.default_error.*
import kotlinx.android.synthetic.main.fragment_economic_indicator_detail.*
import kotlinx.android.synthetic.main.loading.*

/**
 * Created by Anibal Cortez on 10/8/20.
 */

class EconomicIndicatorDetailFragment : BaseFragment() {

    private val economicDetailAdapter = EconomicIndicatorSerieRecyclerViewAdapter()
    private val args: EconomicIndicatorDetailFragmentArgs by navArgs()
    private val  economicIndicatorCode : String by lazy { args.code }
    private val  economicIndicatorName : String by lazy { args.name }
    private val economicIndicatorValue : String by lazy { args.value }
    private var currentView : View? = null
    private val viewModel: EconomicIndicatorDetailViewModel by lazy {
        EconomicIndicatorDetailViewModel(appContainer().getEconomicIndicatorDetailUseCase())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        setUpEconomicIndicatorDetail()
        setUpRecyclerView()
        setUpSwipeRefresh()
        share_frame_layout.setOnClickListener { shareEconomicIndicator() }
        viewModel.getEconomicIndicatorSerie(economicIndicatorCode)
    }


    private fun setUpSwipeRefresh() {
        swipe_refresh_economic_indicator_detail.setOnRefreshListener {
            viewModel.refreshEconomicIndicatorSerie(economicIndicatorCode)
        }
    }

    private fun setUpEconomicIndicatorDetail() {
        item_name.text = economicIndicatorName
        item_code.text = economicIndicatorCode
    }

    private fun setUpRecyclerView() {
        serie_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = economicDetailAdapter
        }
    }

    private fun updateUi(model: EconomicIndicatorDetailViewModel.UiModel) {
        when (model) {
            is EconomicIndicatorDetailViewModel.UiModel.Error -> showdefaultError()
            is EconomicIndicatorDetailViewModel.UiModel.ConnectionError -> showConnectionError()
            is EconomicIndicatorDetailViewModel.UiModel.Loading -> showLoading(model.value)
            is EconomicIndicatorDetailViewModel.UiModel.Success -> showEconomicIndicatorDetail(model.list)
            is EconomicIndicatorDetailViewModel.UiModel.Refresh -> showSwipeRefresh(model.value)
        }
    }

    private fun showSwipeRefresh(value: Boolean) {
        if(swipe_refresh_economic_indicator_detail.isRefreshing){
            swipe_refresh_economic_indicator_detail.isRefreshing = false
        }
    }

    private fun shareEconomicIndicator() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, economicIndicatorName )
            putExtra(Intent.EXTRA_TEXT, economicIndicatorValue)
        }
        intent.resolveActivity(requireActivity().packageManager)?.run {
            startActivity(intent)
        }
    }

    private fun showEconomicIndicatorDetail(serieList: List<Serie>) {
        economicDetailAdapter.setEconomicIndicatorSerieList(serieList)
        hideCurrentView()
        economic_indicator_detail_content.visibility = View.VISIBLE
        currentView = economic_indicator_detail_content
    }

    private fun showdefaultError() {
        hideCurrentView()
        default_error.visibility = View.VISIBLE
        currentView = default_error
    }

    private fun showConnectionError() {
        hideCurrentView()
        connection_error.visibility = View.VISIBLE
        currentView = connection_error
    }

    private fun hideCurrentView() {
        currentView?.visibility = View.GONE
    }

    private fun showLoading(value: Boolean) {
        progress_bar_loading.visibility = if (value) View.VISIBLE else View.GONE
    }

}