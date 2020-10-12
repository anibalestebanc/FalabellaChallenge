package com.falabella.domain.usecase

import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.repository.EconomicIndicatorRepository

class GetEconomicIndicatorListUseCase(private val repository: EconomicIndicatorRepository) {
    suspend fun invoke(forceRefresh : Boolean) : Result<List<EconomicIndicator>> = repository.getEconomicIndicatorList(forceRefresh)
}
