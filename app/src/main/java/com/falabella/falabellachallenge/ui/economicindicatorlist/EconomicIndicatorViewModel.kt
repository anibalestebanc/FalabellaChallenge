package com.falabella.falabellachallenge.ui.economicindicatorlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.falabella.domain.model.DataResponse

class EconomicIndicatorViewModel(private val  getEconomicIndicatorUseCase: com.falabella.domain.usecase.GetEconomicIndicatorListUseCase) : ViewModel(){

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = _model

    sealed class UiModel {
        object Error: UiModel()
        object ConnectionError : UiModel()
        data class Loading(val value: Boolean ) : UiModel()
        data class Success(val list: List<com.falabella.domain.model.EconomicIndicator>) : UiModel()
    }

    fun GetEconomicIdicatorList(){

        showLoading(true)
        when(val response = getEconomicIndicatorUseCase.invoke()){
            is DataResponse.Success -> _model.value = UiModel.Success(response.data)
            is DataResponse.ServerError -> _model.value = UiModel.Error
            is DataResponse.ConnectionError -> _model.value = UiModel.ConnectionError
        }
        showLoading(false)
    }

    private fun showLoading(value: Boolean){
        _model.value = UiModel.Loading(value)
    }
}

