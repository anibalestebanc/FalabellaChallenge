package com.falabella.data.repository

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.repository.EconomicIndicatorRepository

class EconomicIndicatorRepositoryImpl(
    private val localDataSource: EconomicIndicatorLocalDataSource,
    private val remoteDataSource: EconomicIndicatorRemoteDataSource
)
    : EconomicIndicatorRepository {

    override suspend fun getEconomicIndicatorList(): DataResponse<List<EconomicIndicator>> {
        if (localDataSource.isEconomicIndicatorListEmpty()){
            val result =  remoteDataSource.getEconomicIndicatorList()
            if (result is DataResponse.Success){
                localDataSource.saveEconomicIndicatorList(result.data)
            }else{
                return result
            }
        }
        return localDataSource.getEconomicIndicatorList()

        /*Thread.sleep(5000)

        val economicIndicator1 = EconomicIndicator(
            "ipc",
            "Indice de Precios al Consumidor (IPC)",
            "Porcentaje",
            "2020-08-01T04:00:00.000Z",
            "0.1"
        )
        val economicIndicator2 = EconomicIndicator(
            "utm",
            "Unidad Tributaria Mensual (UTM)",
            "Pesos",
            "2020-10-01T03:00:00.000Z",
            "50372"
        )
        val economicIndicator3 = EconomicIndicator(
            "imacec",
            "Imacec",
            "Porcentaje",
            "2020-08-01T04:00:00.000Z",
            "-11.3"
        )
        val economicIndicatorList = listOf(economicIndicator1, economicIndicator2, economicIndicator3)
        return DataResponse.Success(economicIndicatorList) */
    }
}