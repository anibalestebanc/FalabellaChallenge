package com.falabella.challenge

import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.model.Result
import com.falabella.testshared.economicIndicatorDetailMock
import com.falabella.testshared.economicIndicatorListMock

/**
 * Created by Anibal Cortez on 10/13/20.
 */
class FakeEconomicIndicatorRemoteDataSourceImpl : EconomicIndicatorRemoteDataSource {

    var mockedListResult: Result<List<EconomicIndicator>> = Result.Success(economicIndicatorListMock)

    var mockedDetailResult : Result<EconomicIndicatorDetail> = Result.Success(economicIndicatorDetailMock)

    override suspend fun getEconomicIndicatorList(): Result<List<EconomicIndicator>> = mockedListResult

    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String): Result<EconomicIndicatorDetail> = mockedDetailResult

}