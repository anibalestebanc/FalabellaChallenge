package com.falabella.challenge

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.testshared.economicIndicatorDetailMock
import com.falabella.testshared.economicIndicatorListMock

/**
 * Created by Anibal Cortez on 10/13/20.
 */
class FakeEconomicIndicatorLocalDataSourceImpl : EconomicIndicatorLocalDataSource {

    var emptyList : Boolean = false
    var emptyDetail : Boolean = false

    var mockedListResult : List<EconomicIndicator> = economicIndicatorListMock
    var mockedDetailResult : EconomicIndicatorDetail = economicIndicatorDetailMock


    override suspend fun getEconomicIndicatorList(): List<EconomicIndicator> = mockedListResult

    override suspend fun isEconomicIndicatorListEmpty(): Boolean = emptyList

    override suspend fun saveEconomicIndicatorList(list: List<EconomicIndicator>) {
        mockedListResult = list
    }

    override suspend fun isEconomicIndicatorDetailEmpty(economicIndicatorCode: String): Boolean = emptyDetail


    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String): EconomicIndicatorDetail = mockedDetailResult

    override suspend fun saveEconomicIndicatorDetail(economicIndicatorDetail: EconomicIndicatorDetail) {
        mockedDetailResult = economicIndicatorDetail
    }

}