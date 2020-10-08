package com.falabella.domain.usecase

import com.falabella.domain.model.DataResponse
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.repository.EconomicIndicatorRepository

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class GetEconomicIndicatorDetailUseCase(private val repository : EconomicIndicatorRepository) {
    suspend fun invoke(economicIndicatorCode : String, forceRefresh : Boolean) : DataResponse<EconomicIndicatorDetail>
            = repository.getEconomicIndicatorSerie(economicIndicatorCode, forceRefresh)
}