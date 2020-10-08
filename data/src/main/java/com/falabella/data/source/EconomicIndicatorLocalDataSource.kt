package com.falabella.data.source

import com.falabella.domain.model.EconomicIndicator

interface EconomicIndicatorLocalDataSource {
   suspend fun getEconomicIndicatorList() : List<EconomicIndicator>
   suspend fun isEconomicIndicatorListEmpty(): Boolean
   suspend fun saveEconomicIndicatorList(list : List<EconomicIndicator>)
}