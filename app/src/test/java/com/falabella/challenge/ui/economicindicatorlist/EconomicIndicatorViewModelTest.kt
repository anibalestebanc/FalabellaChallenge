package com.falabella.challenge.ui.economicindicatorlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import androidx.lifecycle.Observer
import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.challenge.ui.MainCoroutineRule
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import com.falabella.testshared.economicIndicatorListMock
import com.falabella.testshared.economicIndicatorMock
import com.falabella.testshared.serverErrorMock
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class EconomicIndicatorViewModelTest{

    private val listUseCase: GetEconomicIndicatorListUseCase = mock()
    private val observer: Observer<EconomicIndicatorViewModel.UiModel> = mock()

    private val viewModel = EconomicIndicatorViewModel(listUseCase, Dispatchers.Unconfined)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `When the get economic indicator list is called,  the uses cases is invoked`(){
        runBlocking {
            val forceRefresh = false

            viewModel.getEconomicIdicatorList()

            verify(listUseCase, times(1)).invoke(forceRefresh)
        }
    }

    @Test
    fun `When the uses case return empty list the view model should be call to success model whit empty list`(){

        runBlocking {
            val forceRefresh = false

            val emptyList : Result<List<EconomicIndicator>> = Result.Success(emptyList())

            whenever(listUseCase.invoke(forceRefresh)).thenReturn(emptyList)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(emptyList()))
        }

    }

    @Test
    fun `When the uses case return success the view model should be call to success model whit the same list`()
    {
        runBlocking {
            val forceRefresh = false

            val economicIndicatorResponse = Result.Success(economicIndicatorListMock)

            whenever(listUseCase.invoke(forceRefresh)).thenReturn(economicIndicatorResponse)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(economicIndicatorListMock))

        }
    }


    @Test
    fun `If the uses case return error the view model should be call to UiModel with the error`(){

        runBlocking {
            val forceRefresh = false

            val serverErrorResponse  = Result.ServerError(serverErrorMock)

            whenever(listUseCase.invoke(forceRefresh)).thenReturn(serverErrorResponse)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Error)
        }

    }

    @Test
    fun `If the uses case return connection error the view model should be call to UiModel with the connection error`(){

        runBlocking {
            val forceRefresh = false

            val connectionError  = Result.ConnectionError

            whenever(listUseCase.invoke(forceRefresh)).thenReturn(connectionError)

            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.ConnectionError)
        }

    }

    @Test
    fun `When the view model call to uses case, always should be call to ui model with loading`(){

        runBlocking {
            viewModel.model.observeForever(observer)

            viewModel.getEconomicIdicatorList()

            verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading)

        }
    }

}