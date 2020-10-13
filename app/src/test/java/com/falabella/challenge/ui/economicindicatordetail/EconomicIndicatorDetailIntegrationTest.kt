package com.falabella.challenge.ui.economicindicatordetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.falabella.challenge.AppContainerTest
import com.falabella.challenge.AppModuleTest
import com.falabella.domain.model.Result
import com.falabella.testshared.economicIndicatorDetailMock
import com.falabella.testshared.serverErrorMock
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Anibal Cortez on 10/12/20.
 */
class EconomicIndicatorDetailIntegrationTest {


    private val appModule : AppModuleTest = AppModuleTest()
    private val appContainer : AppContainerTest = AppContainerTest(appModule)

    private val observer: Observer<EconomicIndicatorDetailViewModel.UiModel> = mock()

    private lateinit var viewModel: EconomicIndicatorDetailViewModel


    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        viewModel = EconomicIndicatorDetailViewModel(appContainer.getEconomicIndicatorDetailUseCase(),Dispatchers.Unconfined)
    }

    @Test
    fun ObserveIfLoadingIsCallWhenCallToEconomicIndicatorDetail(){

        val forceRefresh = false

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorDetailMock.code, forceRefresh)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Loading)
    }

    @Test
    fun ObserveIfRefreshIsCallWhenCallToRefreshEconomicIndicatorDetail(){


        viewModel.model.observeForever(observer)

        viewModel.refreshEconomicIndicatorDetail(economicIndicatorDetailMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Refresh)
    }


    @Test
    fun ObserveWhenRemoteDataSourceReturnConnectionError(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyDetail = true

        remoteDataSource.mockedDetailResult = Result.ConnectionError

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorDetailMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.ConnectionError)
    }

    @Test
    fun ObserveWhenRemoteDataSourceReturnServerError(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyDetail = true

        remoteDataSource.mockedDetailResult = Result.ServerError(serverErrorMock)

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorDetailMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Error)
    }


    @Test
    fun ObserveWhenRepositoryReturSuccessDetailFromRemoteDatasource(){

        val localDataSource = appModule.getFakeLocalDataSource()
        val remoteDataSource = appModule.getFakeRemoteDataSource()

        localDataSource.emptyDetail = true

        remoteDataSource.mockedDetailResult = Result.Success(economicIndicatorDetailMock)

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorDetailMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(
            economicIndicatorDetailMock))
    }

    @Test
    fun ObserveWhenRepositoryReturDetailFromLocalDatasource(){

        val localDataSource = appModule.getFakeLocalDataSource()

        localDataSource.emptyDetail = false
        localDataSource.mockedDetailResult = economicIndicatorDetailMock

        viewModel.model.observeForever(observer)

        viewModel.getEconomicIndicatorDetail(economicIndicatorDetailMock.code)

        verify(observer).onChanged(EconomicIndicatorDetailViewModel.UiModel.Success(
            economicIndicatorDetailMock))
    }

}