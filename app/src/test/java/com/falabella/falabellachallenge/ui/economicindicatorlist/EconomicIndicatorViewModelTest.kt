package com.falabella.falabellachallenge.ui.economicindicatorlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test
import androidx.lifecycle.Observer
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator

class EconomicIndicatorViewModelTest{

    private val getEconomicIndicatorUseCase: com.falabella.domain.usecase.GetEconomicIndicatorListUseCase = mock()
    private val viewModel : EconomicIndicatorViewModel = EconomicIndicatorViewModel(getEconomicIndicatorUseCase)

    private val observer: Observer<EconomicIndicatorViewModel.UiModel> = mock()


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `EconomicIndicator called usecase when GetEconomicIdicatorList is call`(){

        viewModel.GetEconomicIdicatorList()

        verify(getEconomicIndicatorUseCase, times(1)).invoke()
    }

    @Test
    fun `EconomicIndicator set emptyList when use case return empty list`(){

        val emptyList : DataResponse<List<EconomicIndicator>> = DataResponse.Success(emptyList())

        whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(emptyList)

        viewModel.model.observeForever(observer)

        viewModel.GetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(emptyList()))
    }

    @Test
    fun `EconomicIndicator set data when use case return a list`()
    {
        val economicIndicatorList = listOf(EconomicIndicator("uf"))

        val economicIndicatorResponse = DataResponse.Success(economicIndicatorList)

        whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(economicIndicatorResponse)

        viewModel.model.observeForever(observer)

        viewModel.GetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(economicIndicatorList))

    }


    @Test
    fun `EconomicIndicator set error when use case return a serverError`(){

        val serverError = 100
        val serverErrorResponse  = DataResponse.ServerError(serverError)

        whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(serverErrorResponse)

        viewModel.model.observeForever(observer)

        viewModel.GetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Error)

    }

    @Test
    fun `EconomicIndicator set Loading with true when use case is call`(){

        viewModel.model.observeForever(observer)
        viewModel.GetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading(true))
    }

    @Test
    fun `EconomicIndicator set Loading with false when use case is call`(){

        viewModel.model.observeForever(observer)
        viewModel.GetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading(false))
    }

}