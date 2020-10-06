package com.falabella.domain.usecase

import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import com.falabella.domain.repository.EconomicIndicatorRepository

class EconomicIndicatorListUseCaseTest {

    private val repository: com.falabella.domain.repository.EconomicIndicatorRepository = mock()

    private val economicIndicatorUseCase: com.falabella.domain.usecase.GetEconomicIndicatorListUseCase =
        com.falabella.domain.usecase.GetEconomicIndicatorListUseCase(repository)

    @Test
    fun `EconomicIndicatorList use case call to repository`(){

        economicIndicatorUseCase.invoke()

        verify(repository).getEconomicIndicatorList()
    }
}