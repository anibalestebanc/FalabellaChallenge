package com.falabella.falabellachallenge.ui.economicindicatordetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.Serie
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import kotlinx.coroutines.launch

class EconomicIndicatorDetailViewModel(private val economicIndicatorDetailUseCase: GetEconomicIndicatorDetailUseCase)
    : ViewModel(){

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get(){
        return _model
    }

    sealed class UiModel {
        object Error: UiModel()
        object ConnectionError : UiModel()
        data class Loading(val value: Boolean ) : UiModel()
        data class Success(val list: List<Serie>) : UiModel()
    }

    fun getEconomicIndicatorSerie(economicIndicatorCode: String){
        viewModelScope.launch {
            _model.value = UiModel.Loading(true)
           val result= economicIndicatorDetailUseCase.invoke(economicIndicatorCode)
            when(result){
                is DataResponse.ConnectionError -> _model.value = UiModel.ConnectionError
                is DataResponse.ServerError -> _model.value = UiModel.Error
                is DataResponse.Success -> _model.value = UiModel.Success(result.data.serieList)
            }
            _model.value = UiModel.Loading(false)
        }
    }
}
