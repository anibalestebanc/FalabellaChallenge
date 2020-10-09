package com.falabella.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import com.falabella.domain.repository.EconomicIndicatorRepository
import kotlinx.coroutines.runBlocking

class EconomicIndicatorListUseCaseTest {

    private val repository: EconomicIndicatorRepository = mock()

    private val economicIndicatorUseCase: GetEconomicIndicatorListUseCase =
        GetEconomicIndicatorListUseCase(repository)

    @Test
    fun `EconomicIndicatorList use case call to repository`(){

        runBlocking{
            economicIndicatorUseCase.invoke(false)

            verify(repository).getEconomicIndicatorList(false)
        }
    }
}