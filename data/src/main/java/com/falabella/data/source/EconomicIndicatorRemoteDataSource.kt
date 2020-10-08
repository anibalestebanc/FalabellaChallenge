package com.falabella.data.source

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail

interface EconomicIndicatorRemoteDataSource {
    suspend fun getEconomicIndicatorList() : DataResponse<List<EconomicIndicator>>
    suspend fun getEconomicIndicatorSerieList(economicIndicatorCode : String) : DataResponse<EconomicIndicatorDetail>
}