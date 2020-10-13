package com.falabella.challenge.ui.economicindicatorlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.falabella.challenge.AppContainerTest
import com.falabella.challenge.AppModuleTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import com.falabella.domain.model.Result
import com.falabella.testshared.economicIndicatorListMock
import com.falabella.testshared.serverErrorMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Anibal Cortez on 10/12/20.
 */
class EconomicIndicatorListIntegrationTest {


    private val appModule : AppModuleTest = AppModuleTest()
    private val appContainer : AppContainerTest = AppContainerTest(appModule)

    private val observer: Observer<EconomicIndicatorViewModel.UiModel> = mock()

    private lateinit var viewModel: EconomicIndicatorViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = EconomicIndicatorViewModel(appContainer.getEconomicIndicatorListUseCase(),Dispatchers.Unconfined)
    }


    @Test
    fun ObserveIfLoadingIsCallWhenCallToEconomicIndicatorList(){

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Loading)
    }

    @Test
    fun ObserveIfRefreshIsCallWhenCallToRefreshEconomicIndicatorList(){

        viewModel.model.observeForever(observer)

        viewModel.forceGetEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Refresh)
    }


    @Test
    fun ObserveWhenRemoteDataSourceReturnConnectionError(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyList = true

        remoteDataSource.mockedListResult = Result.ConnectionError

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.ConnectionError)
    }

    @Test
    fun ObserveWhenRemoteDataSourceReturnServerError(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyList = true

        remoteDataSource.mockedListResult = Result.ServerError(serverErrorMock)

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Error)
    }

    @Test
    fun ObserveWhenRepositoryReturEmptyListFromRemoteDatasource(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyList = true
        remoteDataSource.mockedListResult = Result.Success(emptyList())

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(emptyList()))
    }


    @Test
    fun ObserveWhenRepositoryReturListFromRemoteDatasource(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyList = true
        remoteDataSource.mockedListResult = Result.Success(economicIndicatorListMock)

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(economicIndicatorListMock))
    }

    @Test
    fun ObserveWhenRepositoryReturListFromLocalDatasource(){

        val localDataSource = appModule.getFakeLocalDataSource()

        localDataSource.emptyList = false
        localDataSource.mockedListResult = economicIndicatorListMock

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIdicatorList()

        verify(observer).onChanged(EconomicIndicatorViewModel.UiModel.Success(economicIndicatorListMock))
    }


}