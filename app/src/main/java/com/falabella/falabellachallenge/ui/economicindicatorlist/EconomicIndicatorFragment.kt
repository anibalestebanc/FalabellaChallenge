package com.falabella.falabellachallenge.ui.economicindicatorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import com.falabella.falabellachallenge.MainActivity
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.common.ConnectionHelper
import com.falabella.falabellachallenge.data.database.LocalDatabase
import com.falabella.falabellachallenge.data.database.economicindicator.EconomicIndicatorLocalDataSourceImpl
import com.falabella.falabellachallenge.data.server.RetrofitClient
import com.falabella.falabellachallenge.data.server.economicindicator.EconomicIndicatorRemoteDataSourceImpl
import com.falabella.falabellachallenge.ui.economicindicatorlist.economicindicatoritem.EconomicIndicatorRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_economic_indicator_list.*
import java.lang.reflect.Array

class EconomicIndicatorFragment : Fragment() {

    private lateinit var economicIndicatorAdapter: EconomicIndicatorRecyclerViewAdapter
    private val viewModel : EconomicIndicatorViewModel by lazy {
        GetEconomicIndicatorViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_list, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        viewModel.getEconomicIdicatorList()
    }

    private fun updateUi(model: EconomicIndicatorViewModel.UiModel) {
        when(model){
            is EconomicIndicatorViewModel.UiModel.Loading -> showLoading(model.value)
            is EconomicIndicatorViewModel.UiModel.ConnectionError -> showConnectionError()
            is EconomicIndicatorViewModel.UiModel.Error -> showDefaultError()
            is EconomicIndicatorViewModel.UiModel.Success -> showEconomicIndicatorList(model.list)
        }
    }

    private fun onEconomicIndicatorClicked(economicIndicator : EconomicIndicator){
        (activity as MainActivity).showEconomicIndicatorDetail(economicIndicator.code,economicIndicator.name, economicIndicator.value)
    }

    private fun showEconomicIndicatorList(list: List<EconomicIndicator>) {
        economicIndicatorAdapter = EconomicIndicatorRecyclerViewAdapter(::onEconomicIndicatorClicked, list)
        economic_indicator_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
                adapter = economicIndicatorAdapter
        }

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

        val sortedlist  = resources.getStringArray(R.array.sorted_by_array)
        val spinnerAdapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, sortedlist)
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

    private fun showDefaultError() {

    }

    private fun showConnectionError() {

    }

    private fun showLoading(value: Boolean) {
        progress_bar_view.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun GetEconomicIndicatorViewModel() : EconomicIndicatorViewModel {

        val localDataBase = Room.databaseBuilder(
            activity!!.applicationContext,
            LocalDatabase::class.java, "falabella-db"
        ).build()

        val economicIndicatorService = RetrofitClient.economicIndicatorService
        val connectionHelper = ConnectionHelper(context!!)
        val localDataSource : EconomicIndicatorLocalDataSource = EconomicIndicatorLocalDataSourceImpl(
            localDataBase
        );
        val remoteDataSource : EconomicIndicatorRemoteDataSource = EconomicIndicatorRemoteDataSourceImpl(
            connectionHelper, economicIndicatorService);
        val repository = EconomicIndicatorRepositoryImpl(localDataSource, remoteDataSource)
        val getEconomicIndicatorUseCase = GetEconomicIndicatorListUseCase(repository)
        return EconomicIndicatorViewModel(getEconomicIndicatorUseCase)
    }

}


