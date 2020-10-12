package com.falabella.data.source

import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail

interface EconomicIndicatorLocalDataSource {
   suspend fun getEconomicIndicatorList() : List<EconomicIndicator>
   suspend fun isEconomicIndicatorListEmpty(): Boolean
   suspend fun saveEconomicIndicatorList(list : List<EconomicIndicator>)

   suspend fun isEconomicIndicatorDetailEmpty(economicIndicatorCode : String) : Boolean
   suspend fun getEconomicIndicatorDetail(economicIndicatorCode : String) : EconomicIndicatorDetail
   suspend fun saveEconomicIndicatorDetail(economicIndicatorDetail: EconomicIndicatorDetail)
}