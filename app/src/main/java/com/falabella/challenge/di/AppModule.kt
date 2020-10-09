package com.falabella.challenge.di

import com.falabella.data.repository.EconomicIndicatorRepositoryImpl
import com.falabella.domain.repository.EconomicIndicatorRepository
import com.falabella.challenge.common.ConnectionHelper
import com.falabella.challenge.data.database.LocalDatabase
import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorLocalDataSourceImpl
import com.falabella.challenge.data.server.RetrofitClient
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorRemoteDataSourceImpl
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorService

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class AppModule {

    fun getRepository(localDataBase: LocalDatabase, connectionHelper: ConnectionHelper)
            : EconomicIndicatorRepository =
        EconomicIndicatorRepositoryImpl(
            localDataSource(localDataBase),
            remoteDataSource(connectionHelper)
        )

    private fun getEconomicIndicatorService(): EconomicIndicatorService =
        RetrofitClient.economicIndicatorService


    private fun localDataSource(localDataBase: LocalDatabase) =
        EconomicIndicatorLocalDataSourceImpl(localDataBase)

    private fun remoteDataSource(connectionHelper: ConnectionHelper) =
        EconomicIndicatorRemoteDataSourceImpl(connectionHelper, getEconomicIndicatorService())

}