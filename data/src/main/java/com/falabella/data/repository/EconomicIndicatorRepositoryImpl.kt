package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.repository.EconomicIndicatorRepository

class EconomicIndicatorRepositoryImpl(
    private val localDataSource: EconomicIndicatorLocalDataSource,
    private val remoteDataSource: EconomicIndicatorRemoteDataSource
): EconomicIndicatorRepository {

    override suspend fun getEconomicIndicatorList(): DataResponse<List<EconomicIndicator>> {
        if (localDataSource.isEconomicIndicatorListEmpty()){
            val result =  remoteDataSource.getEconomicIndicatorList()
            if (result is DataResponse.Success)
                localDataSource.saveEconomicIndicatorList(result.data)
            else return result
        }
        return DataResponse.Success(localDataSource.getEconomicIndicatorList())
    }

    override suspend fun getEconomicIndicatorSerie(economicIndicatorCode: String): DataResponse<EconomicIndicatorDetail> {

        if (localDataSource.getEconomicIndicatorDetailIsEmpty(economicIndicatorCode)){
            val result =  remoteDataSource.getEconomicIndicatorSerieList(economicIndicatorCode)
            if (result is DataResponse.Success)
                localDataSource.saveEconomicIndicatorDetail(result.data)
            else return result
        }
        return DataResponse.Success(localDataSource.getEconomicIndicatorDetail(economicIndicatorCode))
    }
}