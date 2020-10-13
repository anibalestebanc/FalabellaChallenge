package com.falabella.challenge.ui.economicindicatordetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.falabella.challenge.ui.MainCoroutineRule
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import com.falabella.domain.model.Result
import com.falabella.testshared.economicIndicatorDetailMock
import com.falabella.testshared.economicIndicatorMock
import com.falabella.testshared.serverErrorMock
import org.junit.Rule
import org.junit.Test


/**
 * Created by Anibal Cortez on 10/9/20.
 */
class EconomicIndicatorDetailViewModelTest {

    private val detailUseCase: GetEconomicIndicatorDetailUseCase = mock()
    private val observer: Observer<EconomicIndicatorDetailViewModel.UiModel> = mock()

    private val viewModel = EconomicIndicatorDetailViewModel(detailUseCase, Dispatchers.Unconfined)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `When economic indicator detail is called the view model always should be call to loading UiModel`() {


        val forceRefresh = false

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorMock.code, forceRefresh)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Loading)
    }

    @Test
    fun `When the force economic indicator detail is called the view model always should be call to refresh UiModel`() {


        viewModel.model.observeForever(observer)

        viewModel.refreshEconomicIndicatorDetail(economicIndicatorMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Refresh)
    }

    @Test
    fun `When the use case return connection error the view model should be call to connection error UiModel`() {

        runBlocking {
            whenever(detailUseCase.invoke(any(), any())).thenReturn(Result.ConnectionError)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorMock.code)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.ConnectionError)
        }
    }

    @Test
    fun `When the use case return error the view model should be call to error UiModel`() {

        runBlocking {

            val expectedResult = Result.ServerError(serverErrorMock)
            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorMock.code)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Error)
        }
    }

    @Test
    fun `When the use case return success the view model should be call to success UiModel with the same value`() {

        runBlocking {

            val expectedResult = Result.Success(economicIndicatorDetailMock)

            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorMock.code)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(economicIndicatorDetailMock))
        }
    }

    @Test
    fun `When the user call refresh and the use case return success the view model should be call to success UiModel with the same value`() {

        runBlocking {

            val expectedResult = Result.Success(economicIndicatorDetailMock)

            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.refreshEconomicIndicatorDetail(economicIndicatorMock.code)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(
                economicIndicatorDetailMock))
        }
    }

}