package com.falabella.falabellachallenge.ui.economicindicatorlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import kotlinx.coroutines.launch

class EconomicIndicatorViewModel(private val getEconomicIndicatorUseCase: GetEconomicIndicatorListUseCase)
    : ViewModel(){

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get(){
        return _model
    }

    sealed class UiModel {
        object Error: UiModel()
        object ConnectionError : UiModel()
        data class Refresh(val value: Boolean) : UiModel()
        data class Loading(val value: Boolean ) : UiModel()
        data class Success(val list: List<EconomicIndicator>) : UiModel()
    }

    fun getEconomicIdicatorList(forceRefresh : Boolean = false){
        viewModelScope.launch{
            _model.value = UiModel.Loading(true)

           val response = getEconomicIndicatorUseCase.invoke(forceRefresh)
            when(response){
                is DataResponse.Success -> _model.value = UiModel.Success(response.data)
                is DataResponse.ServerError -> _model.value = UiModel.Error
                is DataResponse.ConnectionError -> _model.value = UiModel.ConnectionError
            }
            _model.value = UiModel.Loading(false)
        }
    }

    fun forceGetEconomicIdicatorList() {
        viewModelScope.launch {
            val response = getEconomicIndicatorUseCase.invoke(true)
            when(response){
                is DataResponse.Success -> _model.value = UiModel.Success(response.data)
                is DataResponse.ServerError -> _model.value = UiModel.Error
                is DataResponse.ConnectionError -> _model.value = UiModel.ConnectionError
            }
            _model.value = UiModel.Refresh(true)
        }
    }
}

