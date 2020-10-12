package com.falabella.challenge.data.server.economicindicator

import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Result
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

    override suspend fun getEconomicIndicatorList(): Result<List<EconomicIndicator>> =
        withContext(Dispatchers.IO) {
            if (!connectionHelper.isConnected()) {
                return@withContext Result.ConnectionError
            }
            val result = economicIndicatorService.getEconomicIndicatorList()
            if (!result.isSuccessful || result.body() == null) {
                return@withContext Result.ServerError(result.code())
            }
            val economicIndicatorList: List<EconomicIndicator> =
                result.body()!!.toEconomicIndicatorList()
            return@withContext Result.Success(economicIndicatorList)
        }

    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String): Result<EconomicIndicatorDetail> =
        withContext(Dispatchers.IO) {

            if (!connectionHelper.isConnected()) {
                return@withContext Result.ConnectionError
            }
            val result = economicIndicatorService.getEconomicIndicatorDetail(economicIndicatorCode)

            if (!result.isSuccessful || result.body() == null) {
                return@withContext Result.ServerError(result.code())
            }
            val economicIndicatorDetail : EconomicIndicatorDetail = result.body()!!.toDomain()
            return@withContext Result.Success(economicIndicatorDetail)
        }
}