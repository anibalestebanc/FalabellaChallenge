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

        val economicIndicatorCode = "uf"
        val forceRefresh = false

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorCode, forceRefresh)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Loading)
    }

    @Test
    fun `When the force economic indicator detail is called the view model always should be call to refresh UiModel`() {

        val economicIndicatorCode = "uf"

        viewModel.model.observeForever(observer)

        viewModel.refreshEconomicIndicatorDetail(economicIndicatorCode)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Refresh)
    }

    @Test
    fun `When the use case return connection error the view model should be call to connection error UiModel`() {

        runBlocking {
            val economicIndicatorCode = "uf"
            whenever(detailUseCase.invoke(any(), any())).thenReturn(Result.ConnectionError)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorCode)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.ConnectionError)
        }
    }

    @Test
    fun `When the use case return error the view model should be call to error UiModel`() {

        runBlocking {
            val economicIndicatorCode = "uf"
            val serverError = 500
            val expectedResult = Result.ServerError(serverError)
            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorCode)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Error)
        }
    }

    @Test
    fun `When the use case return success the view model should be call to success UiModel with the same value`() {

        runBlocking {
            val economicIndicatorCode = "uf"

            val economicIndicatorDetail = EconomicIndicatorDetail(
                economicIndicatorCode,
                listOf()
            )

            val expectedResult = Result.Success(economicIndicatorDetail)

            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIndicatorDetail(economicIndicatorCode)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(economicIndicatorDetail))
        }
    }

    @Test
    fun `When the user call refresh and the use case return success the view model should be call to success UiModel with the same value`() {

        runBlocking {
            val economicIndicatorCode = "uf"

            val economicIndicatorDetail = EconomicIndicatorDetail(
                economicIndicatorCode,
                listOf()
            )
            val expectedResult = Result.Success(economicIndicatorDetail)

            whenever(detailUseCase.invoke(any(), any())).thenReturn(expectedResult)

            viewModel.model.observeForever(observer)

            viewModel.refreshEconomicIndicatorDetail(economicIndicatorCode)

            verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(economicIndicatorDetail))
        }
    }

}