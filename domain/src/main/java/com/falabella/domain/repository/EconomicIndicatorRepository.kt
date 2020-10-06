package com.falabella.domain.repository

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator

interface EconomicIndicatorRepository {
     fun getEconomicIndicatorList() : DataResponse<List<EconomicIndicator>>
}
