package com.falabella.falabellachallenge.ui.economicindicatorlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import androidx.lifecycle.Observer
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.falabellachallenge.ui.MainCoroutineRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class EconomicIndicatorViewModelTest{

    private val getEconomicIndicatorUseCase: com.falabella.domain.usecase.GetEconomicIndicatorListUseCase = mock()
    private val viewModel : EconomicIndicatorViewModel = EconomicIndicatorViewModel(getEconomicIndicatorUseCase)

    private val observer: Observer<EconomicIndicatorViewModel.UiModel> = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `EconomicIndicator called usecase when GetEconomicIdicatorList is call`(){
        runBlocking {
            viewModel.getEconomicIdicatorList()

            verify(getEconomicIndicatorUseCase, times(1)).invoke()
        }
    }

    @Test
    fun `EconomicIndicator set emptyList when use case return empty list`(){

        runBlocking {
            val emptyList : DataResponse<List<EconomicIndicator>> = DataResponse.Success(emptyList())

            whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(emptyList)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(emptyList()))
        }

    }

    @Test
    fun `EconomicIndicator set data when use case return a list`()
    {
        runBlocking {
            val utm = EconomicIndicator(
                "utm",
                "Unidad Tributaria Mensual (UTM)",
                "Pesos",
                "2020-10-01T03:00:00.000Z",
                "50372"
            )
            val economicIndicatorList = listOf(utm)

            val economicIndicatorResponse = DataResponse.Success(economicIndicatorList)

            whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(economicIndicatorResponse)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(economicIndicatorList))

        }
    }


    @Test
    fun `EconomicIndicator set error when use case return a serverError`(){

        runBlocking {
            val serverError = 100
            val serverErrorResponse  = DataResponse.ServerError(serverError)

            whenever(getEconomicIndicatorUseCase.invoke()).thenReturn(serverErrorResponse)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Error)

        }

    }

    @Test
    fun `EconomicIndicator set Loading with true when use case is call`(){

        runBlocking {
            viewModel.model.observeForever(observer)
            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading(true))

        }
    }

    @Test
    fun `EconomicIndicator set Loading with false when use case is call`(){

        viewModel.model.observeForever(observer)
        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading(false))
    }

}