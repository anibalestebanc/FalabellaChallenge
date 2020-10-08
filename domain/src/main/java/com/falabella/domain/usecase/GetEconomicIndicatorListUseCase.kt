package com.falabella.domain.usecase

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.repository.EconomicIndicatorRepository

class GetEconomicIndicatorListUseCase(private val repository: EconomicIndicatorRepository) {
    suspend fun invoke() : DataResponse<List<EconomicIndicator>> = repository.getEconomicIndicatorList()
}
