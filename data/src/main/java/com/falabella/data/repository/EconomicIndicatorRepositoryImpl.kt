package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.repository.EconomicIndicatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EconomicIndicatorRepositoryImpl(
    private val localDataSource: EconomicIndicatorLocalDataSource,
    private val remoteDataSource: EconomicIndicatorRemoteDataSource
): EconomicIndicatorRepository {

    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String, forceRefresh : Boolean): Result<EconomicIndicatorDetail> {

        if (forceRefresh || localDataSource.isEconomicIndicatorDetailEmpty(economicIndicatorCode)){
            val result =  remoteDataSource.getEconomicIndicatorDetail(economicIndicatorCode)
            if (result is Result.Success)
                localDataSource.saveEconomicIndicatorDetail(result.data)
            else return result
        }
        return Result.Success(localDataSource.getEconomicIndicatorDetail(economicIndicatorCode))
    }

    override fun getEconomicIndicatorList(forceRefresh : Boolean): Flow<Result<List<EconomicIndicator>>> = flow {
        if (!forceRefresh && !localDataSource.isEconomicIndicatorListEmpty()){
            emit(Result.Success(localDataSource.getEconomicIndicatorList()))
        }
        //condition to call server
        val result = remoteDataSource.getEconomicIndicatorList()
        if (result is Result.Success){
            localDataSource.saveEconomicIndicatorList(result.data)
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}