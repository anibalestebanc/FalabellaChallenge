package com.falabella.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import com.falabella.domain.repository.EconomicIndicatorRepository
import kotlinx.coroutines.runBlocking

class EconomicIndicatorListUseCaseTest {

    private val repository: EconomicIndicatorRepository = mock()

    private val listUseCase: GetEconomicIndicatorListUseCase =
        GetEconomicIndicatorListUseCase(repository)

    @Test
    fun `getEconomicIndicatorList of repository is called when the uses case call invoke`(){

        runBlocking{
            val forceRefresh = false

            listUseCase.invoke(forceRefresh)

            verify(repository).getEconomicIndicatorList(forceRefresh)
        }
    }
}