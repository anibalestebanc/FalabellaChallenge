package com.falabella.domain.repository

import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import kotlinx.coroutines.flow.Flow

interface EconomicIndicatorRepository {
     suspend fun getEconomicIndicatorDetail(economicIndicatorCode : String, forceRefresh : Boolean): Result<EconomicIndicatorDetail>
     fun getEconomicIndicatorList(forceRefresh : Boolean) : Flow<Result<List<EconomicIndicator>>>
}
