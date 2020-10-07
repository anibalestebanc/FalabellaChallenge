package com.falabella.data.source

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator

interface EconomicIndicatorRemoteDataSource {
    suspend fun getEconomicIndicatorList() : DataResponse<List<EconomicIndicator>>
}