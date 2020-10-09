package com.falabella.falabellachallenge.ui.economicindicatorlist

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
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.ui.common.BaseFragment
import com.falabella.falabellachallenge.ui.economicindicatorlist.economicindicatoritem.EconomicIndicatorRecyclerViewAdapter
import kotlinx.android.synthetic.main.connection_error.*
import kotlinx.android.synthetic.main.default_error.*
import kotlinx.android.synthetic.main.fragment_economic_indicator_list.*
import kotlinx.android.synthetic.main.loading.*

class EconomicIndicatorFragment : BaseFragment() {

    private val economicIndicatorAdapter = EconomicIndicatorRecyclerViewAdapter(::onEconomicIndicatorClicked)
    private var currentView : View? = null
    private val viewModel : EconomicIndicatorViewModel by lazy {
        EconomicIndicatorViewModel(appContainer().getEconomicIndicatorListUseCase())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))


        setUpRecyclerView()
        setUpSearchView()
        setUpSpinnerSortedBy()
        setUpSwuipeRefresh()
        viewModel.getEconomicIdicatorList()
    }

    private fun setUpSwuipeRefresh() {
        swipe_refresh_economic_indicator_list.setOnRefreshListener {
            viewModel.forceGetEconomicIdicatorList()
        }
    }

    private fun setUpSpinnerSortedBy() {
        val sortedlist  = resources.getStringArray(R.array.sorted_by_array)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sortedlist)
        spinner_sorted_by.adapter = spinnerAdapter

        spinner_sorted_by.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val sertedValue = sortedlist.get(position)
                when(sertedValue){
                    "None" -> economicIndicatorAdapter.sortedClear()
                    "Asc" ->economicIndicatorAdapter.sortedByAsc()
                    "Des"-> economicIndicatorAdapter.sortedByDes()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                economicIndicatorAdapter.sortedClear()
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
        when(model){
            is EconomicIndicatorViewModel.UiModel.Loading -> showLoading(model.value)
            is EconomicIndicatorViewModel.UiModel.ConnectionError -> showConnectionError()
            is EconomicIndicatorViewModel.UiModel.Error -> showDefaultError()
            is EconomicIndicatorViewModel.UiModel.Success -> showEconomicIndicatorList(model.list)
            is EconomicIndicatorViewModel.UiModel.Refresh -> showSwipeRefresh(model.value)
        }
    }

    private fun showSwipeRefresh(value: Boolean) {
        if(swipe_refresh_economic_indicator_list.isRefreshing){
            swipe_refresh_economic_indicator_list.isRefreshing = false
        }

    }

    private fun setUpRecyclerView(){
            economic_indicator_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = economicIndicatorAdapter
            }
    }

    private fun onEconomicIndicatorClicked(economicIndicator : EconomicIndicator){
        val action = EconomicIndicatorFragmentDirections.actionToDetailFragment(
            economicIndicator.name,economicIndicator.code,economicIndicator.value
        )

        findNavController().navigate(action)
    }

    private fun showEconomicIndicatorList(economicIndicatorList: List<EconomicIndicator>) {
        economicIndicatorAdapter.setEconomicIndicatorList(economicIndicatorList)
        hideCurrentView()
        economic_indicator_content.visibility = View.VISIBLE
        currentView = economic_indicator_content
    }

    private fun showDefaultError() {
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


