package com.falabella.falabellachallenge.data.server.economicindicator

import com.falabella.domain.model.DataResponse
import com.falabella.falabellachallenge.common.ConnectionHelper
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
    fun `Should be return connection error when the device doesn't have connection`() {
        runBlocking {
            val expectedResponse = DataResponse.ConnectionError
            whenever(connectionHelper.isConnected()).thenReturn(false)

            val result = remoteDataSourceImpl.getEconomicIndicatorList()
            assertEquals(expectedResponse, result)
        }
    }

}