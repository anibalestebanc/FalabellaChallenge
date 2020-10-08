package com.falabella.falabellachallenge.ui.economicindicatordetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.domain.model.Serie
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.ui.common.BaseFragment
import com.falabella.falabellachallenge.ui.economicindicatordetail.economicindicatorserieitem.EconomicIndicatorSerieRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_economic_indicator_detail.*

/**
 * Created by Anibal Cortez on 10/8/20.
 */

class EconomicIndicatorDetailFragment : BaseFragment() {

    private val viewModel: EconomicIndicatorDetailViewModel by lazy {
        EconomicIndicatorDetailViewModel(appContainer().getEconomicIndicatorDetailUseCase())
    }

    companion object {
        private const val ECONOMIC_INDICATOR_CODE = "ECONOMIC_INDICATOR_CODE"
        private const val ECONOMIC_INDICATOR_NAME = "ECONOMIC_INDICATOR_NAME"
        private const val ECONOMIC_INDICATOR_VALUE = "ECONOMIC_INDICATOR_VALUE"
        fun newInstance(
            code: String,
            name: String,
            value: String
        ): EconomicIndicatorDetailFragment {
            val bundle = Bundle().apply {
                putString(ECONOMIC_INDICATOR_CODE, code)
                putString(ECONOMIC_INDICATOR_NAME, name)
                putString(ECONOMIC_INDICATOR_VALUE, value)
            }
            return EconomicIndicatorDetailFragment().apply { arguments = bundle }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        share_frame_layout.setOnClickListener { shareEconomicIndicator() }

        viewModel.getEconomicIndicatorSerie(arguments!!.getString(ECONOMIC_INDICATOR_CODE)!!)
    }

    private fun updateUi(model: EconomicIndicatorDetailViewModel.UiModel) {
        when (model) {
            is EconomicIndicatorDetailViewModel.UiModel.Error -> showdefaultError()
            is EconomicIndicatorDetailViewModel.UiModel.ConnectionError -> showConnectionError()
            is EconomicIndicatorDetailViewModel.UiModel.Loading -> showLoading(model.value)
            is EconomicIndicatorDetailViewModel.UiModel.Success -> showEconomicIndicatorDetail(model.list)
        }
    }

    private fun shareEconomicIndicator() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, arguments!!.getString(ECONOMIC_INDICATOR_NAME))
            putExtra(Intent.EXTRA_TEXT, arguments!!.getString(ECONOMIC_INDICATOR_VALUE))
            putExtra(Intent.EXTRA_TEXT, arguments!!.getString(ECONOMIC_INDICATOR_VALUE))
        }
        intent.resolveActivity(activity!!.packageManager)?.run {
            startActivity(intent)
        }

    }

    private fun showEconomicIndicatorDetail(serieList: List<Serie>) {
        item_name.text = arguments!!.getString(ECONOMIC_INDICATOR_NAME)
        item_code.text = arguments!!.getString(ECONOMIC_INDICATOR_CODE)
        serie_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = EconomicIndicatorSerieRecyclerViewAdapter(serieList)
        }
    }

    private fun showdefaultError() {
        // TODO: 10/8/20
    }

    private fun showConnectionError() {
        // TODO: 10/8/20
    }

    private fun showLoading(value: Boolean) {
        progress_bar_view.visibility = if (value) View.VISIBLE else View.GONE
    }


}