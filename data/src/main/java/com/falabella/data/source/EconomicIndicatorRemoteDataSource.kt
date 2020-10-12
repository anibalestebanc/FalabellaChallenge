package com.falabella.data.source

import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail

interface EconomicIndicatorRemoteDataSource {
    suspend fun getEconomicIndicatorList() : Result<List<EconomicIndicator>>
    suspend fun getEconomicIndicatorDetail(economicIndicatorCode : String) : Result<EconomicIndicatorDetail>
}