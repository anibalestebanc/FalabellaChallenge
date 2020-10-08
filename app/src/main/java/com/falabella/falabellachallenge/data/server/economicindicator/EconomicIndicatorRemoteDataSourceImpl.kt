package com.falabella.falabellachallenge.data.server.economicindicator

import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.falabellachallenge.common.ConnectionHelper
import com.falabella.falabellachallenge.data.mapper.toEconomicIndicatorList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EconomicIndicatorRemoteDataSourceImpl(
    private val connectionHelper: ConnectionHelper,
    private val economicIndicatorService: EconomicIndicatorService)
    : EconomicIndicatorRemoteDataSource {

    override suspend fun getEconomicIndicatorList(): DataResponse<List<EconomicIndicator>> =
        withContext(Dispatchers.IO) {
            if (!connectionHelper.isConnected()) {
                return@withContext DataResponse.ConnectionError
            }
            val result = economicIndicatorService.getEconomicIndicatorList()
            if (!result.isSuccessful || result.body() == null) {
                return@withContext DataResponse.ServerError(result.code())
            }
            val economicIndicatorList: List<EconomicIndicator> =
                result.body()!!.toEconomicIndicatorList()
            return@withContext DataResponse.Success(economicIndicatorList)
        }
}