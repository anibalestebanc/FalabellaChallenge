package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.repository.EconomicIndicatorRepository

class EconomicIndicatorRepositoryImpl constructor(
    private val localDataSource: EconomicIndicatorLocalDataSource,
    private val remoteDataSource: EconomicIndicatorRemoteDataSource
): EconomicIndicatorRepository {

    override suspend fun getEconomicIndicatorList(forceRefresh : Boolean): Result<List<EconomicIndicator>> {
        if (forceRefresh || localDataSource.isEconomicIndicatorListEmpty()){
            val result =  remoteDataSource.getEconomicIndicatorList()
            if (result is Result.Success)
                localDataSource.saveEconomicIndicatorList(result.data)
            else return result
        }
        return Result.Success(localDataSource.getEconomicIndicatorList())
    }

    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String, forceRefresh : Boolean): Result<EconomicIndicatorDetail> {

        if (forceRefresh || localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)){
            val result =  remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)
            if (result is Result.Success)
                localDataSource.saveEconomicIndicatorDetail(result.data)
            else return result
        }
        return Result.Success(localDataSource.getEconomicIndicatorDetail(economicIndicatorCode))
    }
}