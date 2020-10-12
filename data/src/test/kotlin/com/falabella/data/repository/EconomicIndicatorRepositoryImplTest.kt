package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class EconomicIndicatorRepositoryImplTest {

    private val remoteDataSource: EconomicIndicatorRemoteDataSource = mock()
    private val localDataSource: EconomicIndicatorLocalDataSource = mock()
    private val repository: EconomicIndicatorRepositoryImpl =
        EconomicIndicatorRepositoryImpl(localDataSource, remoteDataSource)

    /**
     * Economic indicator list
     */

    @Test
    fun `When the local data source of the list is empty should be call to remote data source`() {
        runBlocking {

            val forceRefresh = false
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorList()
        }
    }

    @Test
    fun `When the force refresh is true, the repository always should be call to remote data source`() {
        runBlocking {

            val forceRefresh = true
            repository.getEconomicIndicatorList(forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorList()
        }
    }

    @Test
    fun `When the local data source has data the repository should be call local data source`() {
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


            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(false)
            whenever(localDataSource.getEconomicIndicatorList()).thenReturn(economicIndicatorList)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource, times(1)).getEconomicIndicatorList()
        }
    }

    @Test
    fun `When the remote data source return error, the repository should be return the same`() {
        runBlocking {
            val forceRefresh = false
            val serverError = 500
            val expectedResult = Result.ServerError(serverError)
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.ServerError(
                    serverError
                )
            )

            val result = repository.getEconomicIndicatorList(forceRefresh)

            assertEquals(expectedResult, result)

        }
    }

    @Test
    fun `If the remote data source return success, the repository should be return the same`() {
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
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.Success(
                    economicIndicatorList
                )
            )
            whenever(localDataSource.getEconomicIndicatorList()).thenReturn(economicIndicatorList)

            val result = repository.getEconomicIndicatorList(forceRefresh)

            assertEquals(Result.Success(economicIndicatorList), result)
        }

    }

    @Test
    fun `If the remote data source return success, the local data source should be call the save method`() {
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
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.Success(
                    economicIndicatorList
                )
            )
            whenever(localDataSource.getEconomicIndicatorList()).thenReturn(economicIndicatorList)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource).saveEconomicIndicatorList(economicIndicatorList)

        }

    }

    @Test
    fun `If the remote data source return connection error, the local data source never calls to save data`() {
        runBlocking {
            val forceRefresh = false

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(Result.ConnectionError)

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }


    @Test
    fun `If the remote data source return server error, the local data source never calls to save data`() {
        runBlocking {
            val forceRefresh = false
            val serverError = 500

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.ServerError(
                    serverError
                )
            )

            repository.getEconomicIndicatorList(forceRefresh)

            verify(localDataSource, times(0)).saveEconomicIndicatorList(emptyList())
        }
    }


    /**
     * Economic indicator detail
     */
    @Test
    fun `When the local data source of the detail is empty should be call to remote data source`() {
        runBlocking {

            val forceRefresh = false
            val economicIndicatorCode = "100"
            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(
                true
            )

            repository.getEconomicIndicatorDetail(economicIndicatorCode, forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorCode)
        }
    }

    @Test
    fun `When the detail force refresh is true, the repository always should be call to remote data source`() {
        runBlocking {

            val forceRefresh = true
            val economicIndicatorCode = "uf"
            repository.getEconomicIndicatorDetail(economicIndicatorCode,forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorCode)
        }
    }

    @Test
    fun `When the detail local data source has data then the repository  should be call local data source`() {
        runBlocking {

            val forceRefresh = false
            val economicIndicatorCode = "uf"
            val economicIndicatorDetail = EconomicIndicatorDetail(
                code = economicIndicatorCode,
                serieList = emptyList()
            )

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(false)
            whenever(localDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(economicIndicatorDetail)

            repository.getEconomicIndicatorDetail(economicIndicatorCode,forceRefresh)

            verify(localDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorCode)
        }
    }


    @Test
    fun `When the detail remote data source return server error then the repository should be return server error`() {
        runBlocking {

            val forceRefresh = false
            val economicIndicatorCode = "uf"
            val errorCode = 500
            val expected = Result.ServerError(errorCode)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(Result.ServerError(errorCode))

            val result = repository.getEconomicIndicatorDetail(economicIndicatorCode, forceRefresh)

            assertEquals(expected, result)

        }
    }

    @Test
    fun `When the detail remote data source return connection error then the repository should be return connection error`() {
        runBlocking {

            val forceRefresh = false
            val economicIndicatorCode = "uf"
            val expected = Result.ConnectionError

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(Result.ConnectionError)

            val result = repository.getEconomicIndicatorDetail(economicIndicatorCode, forceRefresh)

            assertEquals(expected, result)

        }
    }

    @Test
    fun `When the detail remote data source return serve error, the local data source never calls to save data`() {
        runBlocking {
            val forceRefresh = false
            val economicIndicatorCode = "uf"
            val errorCode = 500
            val expected = Result.ServerError(errorCode)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(Result.ServerError(errorCode))

            val result = repository.getEconomicIndicatorDetail(economicIndicatorCode,forceRefresh)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `When the detail remote data source return success then the repository should be return the same`() {
        runBlocking {
            val forceRefresh = false
            val economicIndicatorCode = "uf"

            val economicIndicatorDetail = EconomicIndicatorDetail(
                code = economicIndicatorCode,
                serieList = emptyList()
            )
            val expected = Result.Success(economicIndicatorDetail)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(Result.Success(economicIndicatorDetail))
            whenever(localDataSource.getEconomicIndicatorDetail(economicIndicatorCode)).thenReturn(economicIndicatorDetail)

            val result = repository.getEconomicIndicatorDetail(economicIndicatorCode,forceRefresh)

            assertEquals(expected, result)
        }
    }



}