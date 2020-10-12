package com.falabella.challenge.ui.economicindicatordetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.falabella.challenge.ui.common.BaseViewModel
import com.falabella.domain.model.Serie
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import kotlinx.coroutines.launch
import com.falabella.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher

class EconomicIndicatorDetailViewModel(
    private val economicIndicatorDetailUseCase: GetEconomicIndicatorDetailUseCase,
    uiDispatcher: CoroutineDispatcher
) : BaseViewModel(uiDispatcher) {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            return _model
        }

    sealed class UiModel {
        object Error : UiModel()
        object ConnectionError : UiModel()
        data class Refresh(val value: Boolean) : UiModel()
        data class Loading(val value: Boolean) : UiModel()
        data class Success(val list: List<Serie>) : UiModel()
    }

    fun getEconomicIndicatorDetail(economicIndicatorCode: String, forceRefresh: Boolean = false) {
        launch {
            _model.value = UiModel.Loading(true)
            when (val result =
                economicIndicatorDetailUseCase.invoke(economicIndicatorCode, forceRefresh)) {
                is Result.ConnectionError -> _model.value = UiModel.ConnectionError
                is Result.ServerError -> _model.value = UiModel.Error
                is Result.Success -> _model.value = UiModel.Success(result.data.serieList)
            }

        }
    }

    fun refreshEconomicIndicatorDetail(economicIndicatorCode: String) {
        launch {
            when (val result = economicIndicatorDetailUseCase.invoke(economicIndicatorCode, true)) {
                is Result.ConnectionError -> _model.value = UiModel.ConnectionError
                is Result.ServerError -> _model.value = UiModel.Error
                is Result.Success -> _model.value = UiModel.Success(result.data.serieList)
            }
            _model.value = UiModel.Refresh(false)
        }
    }
}
