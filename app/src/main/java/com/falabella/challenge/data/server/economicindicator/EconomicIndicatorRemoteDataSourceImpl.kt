package com.falabella.challenge.data.server.economicindicator

import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.challenge.common.ConnectionHelper
import com.falabella.challenge.data.mapper.toDomain
import com.falabella.challenge.data.mapper.toEconomicIndicatorList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EconomicIndicatorRemoteDataSourceImpl(
    private val connectionHelper: ConnectionHelper,
    private val economicIndicatorService: EconomicIndicatorService
) : EconomicIndicatorRemoteDataSource {

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

    override suspend fun getEconomicIndicatorSerieList(economicIndicatorCode: String): DataResponse<EconomicIndicatorDetail> =
        withContext(Dispatchers.IO) {

            if (!connectionHelper.isConnected()) {
                return@withContext DataResponse.ConnectionError
            }
            val result = economicIndicatorService.getEconomicIndicatorDetail(economicIndicatorCode)

            if (!result.isSuccessful || result.body() == null) {
                return@withContext DataResponse.ServerError(result.code())
            }
            val economicIndicatorDetail : EconomicIndicatorDetail = result.body()!!.toDomain()
            return@withContext DataResponse.Success(economicIndicatorDetail)
        }
}