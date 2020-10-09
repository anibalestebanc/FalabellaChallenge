package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class EconomicIndicatorRepositoryImplTest {

    private val remoteDataSource: EconomicIndicatorRemoteDataSource = mock()
    private val localDataSource: EconomicIndicatorLocalDataSource = mock()
    private val repository: EconomicIndicatorRepositoryImpl =
        EconomicIndicatorRepositoryImpl(localDataSource, remoteDataSource)


    @Test
    fun `GetEconomicIndicatorLis from remote data source is called when local data source is empty`() {
        runBlocking {

            val forceRefresh = false
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorList()
        }
    }



    @Test
    fun `Save indicator list never is called when remote data source return server error`() {
        runBlocking {
            val forceRefresh = false
            val serverError = 500
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                DataResponse.ServerError(
                    serverError
                )
            )

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }

    @Test
    fun `Save indicator list never is called when remote data source return connection error`() {
        runBlocking {
            val forceRefresh = false
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.ConnectionError)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }

    @Test
    fun `local data source return the same data that remote when local data source is empty`()
    {
        runBlocking {
            val forceRefresh = false
            val economicIndicator = EconomicIndicator(
                "ipc",
                "Indice de Precios al Consumidor (IPC)",
                "Porcentaje",
                "2020-08-01T04:00:00.000Z",
                "0.1"
            )
            val economicIndicatorList: List<EconomicIndicator> = listOf(economicIndicator)

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.Success(economicIndicatorList))
            whenever(localDataSource.getEconomicIndicatorList()).thenReturn(economicIndicatorList)

            val result = repository.getEconomicIndicatorList(forceRefresh)

            assertEquals(DataResponse.Success(economicIndicatorList), result)
        }

    }

    @Test
    fun `When Remote data source return error should be the same that repository`()
    {
        runBlocking {
            val forceRefresh = false
            val serverError = 500
            val expectedResult = DataResponse.ServerError(serverError)
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.ServerError(serverError))

            val result = repository.getEconomicIndicatorList(forceRefresh)

            assertEquals(expectedResult, result)

        }
    }
}