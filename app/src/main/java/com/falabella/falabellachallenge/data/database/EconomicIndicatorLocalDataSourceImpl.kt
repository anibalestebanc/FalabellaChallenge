package com.falabella.falabellachallenge.data.database

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EconomicIndicatorLocalDataSourceImpl : EconomicIndicatorLocalDataSource {

    override suspend fun getEconomicIndicatorList(): DataResponse<List<EconomicIndicator>> =
        withContext(Dispatchers.IO) {
            Thread.sleep(5000)

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
            val economicIndicatorList =
                listOf(economicIndicator1, economicIndicator2, economicIndicator3)
            DataResponse.Success(economicIndicatorList)
        }

    override suspend fun isEconomicIndicatorListEmpty(): Boolean =
        withContext(Dispatchers.IO) {
        true
    }

    override suspend fun saveEconomicIndicatorList(list: List<EconomicIndicator>) =
        withContext(Dispatchers.IO) {
        }
}