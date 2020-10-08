package com.falabella.falabellachallenge.ui.economicindicatordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Serie
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import com.falabella.falabellachallenge.R
import com.falabella.falabellachallenge.common.ConnectionHelper
import com.falabella.falabellachallenge.data.database.LocalDatabase
import com.falabella.falabellachallenge.data.database.economicindicator.EconomicIndicatorLocalDataSourceImpl
import com.falabella.falabellachallenge.data.server.RetrofitClient
import com.falabella.falabellachallenge.data.server.economicindicator.EconomicIndicatorRemoteDataSourceImpl
import com.falabella.falabellachallenge.ui.economicindicatordetail.economicindicatorserieitem.EconomicIndicatorSerieRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_economic_indicator_detail.*


/**
 * Created by Anibal Cortez on 10/8/20.
 */

class EconomicIndicatorDetailFragment : Fragment() {

    private val viewModel: EconomicIndicatorDetailViewModel by lazy { getEconomicIndicatorDetailViewModel() }

    companion object {
        private const val ECONOMIC_INDICATOR_CODE = "ECONOMIC_INDICATOR_CODE"
        private const val ECONOMIC_INDICATOR_NAME = "ECONOMIC_INDICATOR_NAME"
        fun newInstance(code: String, name: String): EconomicIndicatorDetailFragment {
            val bundle = Bundle().apply {
                putString(ECONOMIC_INDICATOR_CODE, code)
                putString(ECONOMIC_INDICATOR_NAME, name)
            }
            return EconomicIndicatorDetailFragment().apply { arguments = bundle }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_economic_indicator_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

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

    private fun showEconomicIndicatorDetail(serieList :List<Serie>){
        item_name.text = arguments!!.getString(ECONOMIC_INDICATOR_NAME)
        item_code.text = arguments!!.getString(ECONOMIC_INDICATOR_CODE)
        serie_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = EconomicIndicatorSerieRecyclerViewAdapter(serieList)
        }
    }

    private fun showdefaultError(){
        // TODO: 10/8/20
    }
    private fun showConnectionError(){
        // TODO: 10/8/20
    }

    private fun showLoading(value : Boolean){
        progress_bar_view.visibility = if (value) View.VISIBLE else View.GONE
    }

    private fun getEconomicIndicatorDetailViewModel(): EconomicIndicatorDetailViewModel {
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
        val economicIndicatorDetailUseCase = GetEconomicIndicatorDetailUseCase(repository)
        return EconomicIndicatorDetailViewModel(economicIndicatorDetailUseCase)
    }

}