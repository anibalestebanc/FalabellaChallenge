package com.falabella.domain.repository

import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail

interface EconomicIndicatorRepository {
     suspend fun getEconomicIndicatorList(forceRefresh : Boolean) : Result<List<EconomicIndicator>>
     suspend fun getEconomicIndicatorDetail(economicIndicatorCode : String, forceRefresh : Boolean): Result<EconomicIndicatorDetail>
}
