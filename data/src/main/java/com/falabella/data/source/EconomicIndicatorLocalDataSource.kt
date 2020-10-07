package com.falabella.data.source

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator

interface EconomicIndicatorLocalDataSource {
   suspend fun getEconomicIndicatorList() : DataResponse<List<EconomicIndicator>>
   suspend fun isEconomicIndicatorListEmpty(): Boolean
   suspend fun saveEconomicIndicatorList(list : List<EconomicIndicator>)
}