package com.falabella.domain.usecase

import com.falabella.domain.repository.EconomicIndicatorRepository
import com.falabella.testshared.economicIndicatorMock
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Created by Anibal Cortez on 10/12/20.
 */
class GetEconomicIndicatorDetailUseCaseTest{

    private val repository : EconomicIndicatorRepository = mock()

    private val detailUseCase = GetEconomicIndicatorDetailUseCase(repository)

    @Test
    fun `getEconomicIndicatorDetail of repository is called when details uses cases is invoke`(){

        runBlocking {
            val forceRefresh = false

            detailUseCase.invoke(economicIndicatorMock.code, forceRefresh)

            verify(repository, times(1)).getEconomicIndicatorDetail(economicIndicatorMock.code,forceRefresh)
        }
    }
}

