package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.testshared.economicIndicatorDetailMock
import com.falabella.testshared.economicIndicatorMock
import com.falabella.testshared.serverErrorMock
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

            val economicIndicatorList: List<EconomicIndicator> = listOf(economicIndicatorMock)

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

            val expectedResult = Result.ServerError(serverErrorMock)
            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.ServerError(
                    serverErrorMock
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

            val economicIndicatorList: List<EconomicIndicator> = listOf(economicIndicatorMock)

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

            val economicIndicatorList: List<EconomicIndicator> = listOf(economicIndicatorMock)

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

            whenever(localDataSource.isEconomicIndicatorListEmpty()).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorList()).thenReturn(
                Result.ServerError(
                    serverErrorMock
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

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(
                true
            )

            repository.getEconomicIndicatorDetail(economicIndicatorMock.code, forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorMock.code)
        }
    }

    @Test
    fun `When the detail force refresh is true, the repository always should be call to remote data source`() {
        runBlocking {

            val forceRefresh = true

            repository.getEconomicIndicatorDetail(economicIndicatorMock.code,forceRefresh)

            verify(remoteDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorMock.code)
        }
    }

    @Test
    fun `When the detail local data source has data then the repository  should be call local data source`() {
        runBlocking {

            val forceRefresh = false

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(false)
            whenever(localDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(
                economicIndicatorDetailMock)

            repository.getEconomicIndicatorDetail(economicIndicatorMock.code,forceRefresh)

            verify(localDataSource, times(1)).getEconomicIndicatorDetail(economicIndicatorMock.code)
        }
    }


    @Test
    fun `When the detail remote data source return server error then the repository should be return server error`() {
        runBlocking {

            val forceRefresh = false

            val expected = Result.ServerError(serverErrorMock)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(Result.ServerError(
                serverErrorMock))

            val result = repository.getEconomicIndicatorDetail(economicIndicatorMock.code, forceRefresh)

            assertEquals(expected, result)

        }
    }

    @Test
    fun `When the detail remote data source return connection error then the repository should be return connection error`() {
        runBlocking {

            val forceRefresh = false
            val expected = Result.ConnectionError

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(Result.ConnectionError)

            val result = repository.getEconomicIndicatorDetail(economicIndicatorMock.code, forceRefresh)

            assertEquals(expected, result)

        }
    }

    @Test
    fun `When the detail remote data source return serve error, the local data source never calls to save data`() {
        runBlocking {
            val forceRefresh = false

            val expected = Result.ServerError(serverErrorMock)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(true)
            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(Result.ServerError(
                serverErrorMock))

            val result = repository.getEconomicIndicatorDetail(economicIndicatorMock.code,forceRefresh)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `When the detail remote data source return success then the repository should be return the same`() {
        runBlocking {
            val forceRefresh = false

            val expected = Result.Success(economicIndicatorDetailMock)

            whenever(localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorMock.code)).thenReturn(true)

            whenever(remoteDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(Result.Success(
                economicIndicatorDetailMock))
            whenever(localDataSource.getEconomicIndicatorDetail(economicIndicatorMock.code)).thenReturn(
                economicIndicatorDetailMock)

            val result = repository.getEconomicIndicatorDetail(economicIndicatorMock.code,forceRefresh)

            assertEquals(expected, result)
        }
    }

}