package com.falabella.challenge.ui.economicindicatorlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.falabella.challenge.ui.common.BaseViewModel
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import kotlinx.coroutines.launch
import com.falabella.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher

class EconomicIndicatorViewModel(
    private val getEconomicIndicatorUseCase: GetEconomicIndicatorListUseCase,
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
        object Loading : UiModel()
        data class Refresh(val value: Boolean) : UiModel()
        data class Success(val list: List<EconomicIndicator>) : UiModel()
    }

    fun getEconomicIdicatorList(forceRefresh: Boolean = false) {
        launch {
            _model.value = UiModel.Loading
            when (val result = getEconomicIndicatorUseCase.invoke(forceRefresh)) {
                is Result.Success -> _model.value = UiModel.Success(result.data)
                is Result.ServerError -> _model.value = UiModel.Error
                is Result.ConnectionError -> _model.value = UiModel.ConnectionError
            }
        }
    }

    fun forceGetEconomicIdicatorList() {
        launch {
            when (val result = getEconomicIndicatorUseCase.invoke(true)) {
                is Result.Success -> _model.value = UiModel.Success(result.data)
                is Result.ServerError -> _model.value = UiModel.Error
                is Result.ConnectionError -> _model.value = UiModel.ConnectionError
            }
            _model.value = UiModel.Refresh(true)
        }
    }
}

