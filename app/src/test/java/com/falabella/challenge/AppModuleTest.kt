package com.falabella.challenge

import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import com.falabella.domain.repository.EconomicIndicatorRepository

/**
 * Created by Anibal Cortez on 10/13/20.
 */
class AppModuleTest {

    private val fakeLocalDataSource = FakeEconomicIndicatorLocalDataSourceImpl()
    private val fakeRemoteDataSource = FakeEconomicIndicatorRemoteDataSourceImpl()

    fun getRepository(): EconomicIndicatorRepository = EconomicIndicatorRepositoryImpl(localDataSource(), remoteDataSource())

    private fun localDataSource() : EconomicIndicatorLocalDataSource = fakeLocalDataSource

    private fun remoteDataSource(): EconomicIndicatorRemoteDataSource = fakeRemoteDataSource

    fun getFakeLocalDataSource() : FakeEconomicIndicatorLocalDataSourceImpl = fakeLocalDataSource

    fun getFakeRemoteDataSource() : FakeEconomicIndicatorRemoteDataSourceImpl = fakeRemoteDataSource

}