package com.falabella.domain.repository

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail

interface EconomicIndicatorRepository {
     suspend fun getEconomicIndicatorList() : DataResponse<List<EconomicIndicator>>
     suspend fun getEconomicIndicatorSerie(economicIndicatorCode : String): DataResponse<EconomicIndicatorDetail>
}
