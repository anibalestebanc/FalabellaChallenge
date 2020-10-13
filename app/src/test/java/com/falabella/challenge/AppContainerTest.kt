package com.falabella.challenge

import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase


/**
 * Created by Anibal Cortez on 10/13/20.
 */
class AppContainerTest(private val appModule: AppModuleTest) {

    fun getEconomicIndicatorListUseCase() = GetEconomicIndicatorListUseCase(
        appModule.getRepository()
    )

    fun getEconomicIndicatorDetailUseCase() = GetEconomicIndicatorDetailUseCase(
        appModule.getRepository()
    )
}