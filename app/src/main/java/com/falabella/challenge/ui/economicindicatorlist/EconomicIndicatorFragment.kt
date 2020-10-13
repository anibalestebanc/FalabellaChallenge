package com.falabella.challenge.ui.economicindicatorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.falabella.domain.model.EconomicIndicator
import com.falabella.challenge.R
import com.falabella.challenge.ui.common.BaseFragment
import com.falabella.challenge.ui.util.ErrorViewHelper
import com.falabella.challenge.ui.economicindicatorlist.economicindicatoritem.EconomicIndicatorAdapter
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.default_error.*
import kotlinx.android.synthetic.main.fragment_economic_indicator_list.*
import kotlinx.android.synthetic.main.loading.*

class EconomicIndicatorFragment : BaseFragment() {


    private val viewModel: EconomicIndicatorViewModel by lazy {
        EconomicIndicatorViewModel(appContainer().getEconomicIndicatorListUseCase(),appContainer().getCoroutineDispacher())
    }
    private val economicIndicatorAdapter = EconomicIndicatorAdapter(::onEconomicIndicatorClicked)

    private val errorViewHelper: ErrorViewHelper by lazy { initErrorViewHelper() }

    private val economicIndicatorSortedBy: EconomicIndicatorSortedByImpl by lazy {
        EconomicIndicatorSortedByImpl(economicIndicatorAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        setUpRecyclerView()
        setUpSearchView()
        setUpSpinnerSortedBy()
        setUpSwipeRefresh()
        viewModel.getEconomicIndicatorList()
    }

    private fun initErrorViewHelper(): ErrorViewHelper =
        ErrorViewHelper(
            contentView = economic_indicator_content,
            errorView = default_error,
            connectionView = connection_error,
            loadingView = progress_bar_loading
        )

    private fun setUpSwipeRefresh() {
        swipe_refresh_economic_indicator_list.setOnRefreshListener {
            viewModel.refreshEconomicIndicatorList()
        }
    }

    private fun setUpSpinnerSortedBy() {
        val sortedlist = resources.getStringArray(R.array.sorted_by_array)
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sortedlist)
        spinner_sorted_by.adapter = spinnerAdapter

        spinner_sorted_by.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val sertedValue = sortedlist.get(position)
                when (sertedValue) {
                    "None" -> economicIndicatorSortedBy.clearSortedBy()
                    "Asc" -> economicIndicatorSortedBy.sortedByAsc()
                    "Des" -> economicIndicatorSortedBy.sortedByDes()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                economicIndicatorSortedBy.clearSortedBy()
            }
        }
    }

    private fun setUpSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                economicIndicatorAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun updateUi(model: EconomicIndicatorViewModel.UiModel) {
        when (model) {
            is EconomicIndicatorViewModel.UiModel.Loading -> errorViewHelper.showLoading()
            is EconomicIndicatorViewModel.UiModel.ConnectionError -> errorViewHelper.showConnection()
            is EconomicIndicatorViewModel.UiModel.Error -> errorViewHelper.showError()
            is EconomicIndicatorViewModel.UiModel.Success -> showEconomicIndicatorList(model.list)
            is EconomicIndicatorViewModel.UiModel.FinishState -> hideSwipeRefresh()
        }
    }

    private fun showSwipeRefresh() {
        if (!swipe_refresh_economic_indicator_list.isRefreshing) {
            swipe_refresh_economic_indicator_list.isRefreshing = true
        }
    }

    private fun hideSwipeRefresh(){
        if (swipe_refresh_economic_indicator_list.isRefreshing) {
            swipe_refresh_economic_indicator_list.isRefreshing = false
        }
    }

    private fun setUpRecyclerView() {
        economic_indicator_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = economicIndicatorAdapter
        }
    }

    private fun onEconomicIndicatorClicked(economicIndicator: EconomicIndicator) {
        val action = EconomicIndicatorFragmentDirections.actionToDetailFragment(
            economicIndicator.name, economicIndicator.code, economicIndicator.value
        )
        findNavController().navigate(action)
    }

    private fun showEconomicIndicatorList(economicIndicatorList: List<EconomicIndicator>) {
        economicIndicatorAdapter.setEconomicIndicatorList(economicIndicatorList)
        errorViewHelper.showContent()
        showSwipeRefresh()
    }
}


