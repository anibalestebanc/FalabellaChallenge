package com.falabella.challenge.ui.economicindicatorlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.falabella.challenge.ui.common.BaseViewModel
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import kotlinx.coroutines.launch
import com.falabella.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion

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
        data class Success(val list: List<EconomicIndicator>) : UiModel()
        object FinishState : UiModel()
    }

    fun getEconomicIndicatorList() {
        launch {
            setLoadingState()
            getEconomicIndicatorUseCase.invoke(false).collect { setResult(it) }
            setFinishState()
        }
    }

    fun refreshEconomicIndicatorList(){
        launch {
            getEconomicIndicatorUseCase.invoke(true).collect { setResult(it) }
            setFinishState()
        }
    }

    private fun setResult(result: Result<List<EconomicIndicator>>){
        when (result) {
            is Result.Success -> _model.value = UiModel.Success(result.data)
            is Result.ServerError -> _model.value = UiModel.Error
            is Result.ConnectionError -> _model.value = UiModel.ConnectionError
        }
    }

    private fun setLoadingState(){
        _model.value = UiModel.Loading
    }

    private fun setFinishState(){
        _model.value = UiModel.FinishState
    }

}

