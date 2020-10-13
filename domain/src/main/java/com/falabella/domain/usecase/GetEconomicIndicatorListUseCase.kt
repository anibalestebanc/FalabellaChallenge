package com.falabella.domain.usecase

import com.falabella.domain.model.Result
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.repository.EconomicIndicatorRepository
import kotlinx.coroutines.flow.Flow

class GetEconomicIndicatorListUseCase(private val repository: EconomicIndicatorRepository) {
    fun invoke(forceRefresh : Boolean) :  Flow<Result<List<EconomicIndicator>>> = repository.getEconomicIndicatorList(forceRefresh)
}
