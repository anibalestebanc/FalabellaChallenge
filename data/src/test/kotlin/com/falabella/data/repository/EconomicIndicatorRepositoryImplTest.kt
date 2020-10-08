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
    fun `GetEconomicIndicatorList from Local data source is called when there is elements`() {

        runBlocking {

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(false)

            repository.getEconomicIndicatorList()

            verify(localDataSource, times(1)).getEconomicIndicatorList()
        }

    }

    @Test
    fun `GetEconomicIndicatorLis from remote data source is called when local data source is empty`() {
        runBlocking {

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)

            repository.getEconomicIndicatorList()

            verify(remoteDataSource, times(1)).getEconomicIndicatorList()
        }
    }

    @Test
    fun `Save from local data source is called when remote datas ource is call`() {

        runBlocking {
            val economicIndicator1 = EconomicIndicator(
                "ipc",
                "Indice de Precios al Consumidor (IPC)",
                "Porcentaje",
                "2020-08-01T04:00:00.000Z",
                "0.1"
            )
            val economicIndicatorList: List<EconomicIndicator> = listOf(economicIndicator1)

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                DataResponse.Success(
                    economicIndicatorList
                )
            )

            repository.getEconomicIndicatorList()

            verify(localDataSource).saveEconomicIndicatorList(economicIndicatorList)
        }
    }

    @Test
    fun `Save indicator list never is called when remote data source return server error`() {
        runBlocking {

            val serverError = 500
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                DataResponse.ServerError(
                    serverError
                )
            )

            repository.getEconomicIndicatorList()

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }

    @Test
    fun `Save indicator list never is called when remote data source return connection error`() {
        runBlocking {
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.ConnectionError)

            repository.getEconomicIndicatorList()

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }

    @Test
    fun `local data source return the same data that remote when local data source is empty`()
    {
        runBlocking {
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
            whenever(localDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.Success(economicIndicatorList))

            val result = repository.getEconomicIndicatorList()

            assertEquals(DataResponse.Success(economicIndicatorList), result)
        }

    }

    @Test
    fun `When Remote data source return error should be the same that repository`()
    {
        runBlocking {

            val serverError = 500
            val expectedResult = DataResponse.ServerError(serverError)
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(DataResponse.ServerError(serverError))

            val result = repository.getEconomicIndicatorList()

            assertEquals(expectedResult, result)

        }
    }
}