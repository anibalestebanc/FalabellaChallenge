package com.falabella.challenge.data.server.economicindicator

import com.falabella.domain.model.Result
import com.falabella.challenge.common.ConnectionHelper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test


class EconomicIndicatorRemoteDataSourceImplTest {

    private val economicIndicatorService: EconomicIndicatorService = mock()
    private val connectionHelper: ConnectionHelper = mock()

    private val remoteDataSourceImpl = EconomicIndicatorRemoteDataSourceImpl(
        connectionHelper, economicIndicatorService
    )

    @Test
    fun `when the device doesn't have connection the remote data source should be return connection error`() {
        runBlocking {

            val expectedResponse = Result.ConnectionError

            whenever(connectionHelper.isConnected()).thenReturn(false)

            val result = remoteDataSourceImpl.getEconomicIndicatorList()

            assertEquals(expectedResponse, result)
        }
    }

}