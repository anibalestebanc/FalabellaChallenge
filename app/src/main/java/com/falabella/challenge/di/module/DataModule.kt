package com.falabella.challenge.di.module

import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun economicIndicatorRepositoryProvider(
        localDataSource: EconomicIndicatorLocalDataSource,
        remoteDataSource: EconomicIndicatorRemoteDataSource
    ) = EconomicIndicatorRepositoryImpl(localDataSource, remoteDataSource)
}