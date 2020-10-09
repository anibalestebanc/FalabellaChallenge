package com.falabella.challenge.di

import android.app.Activity
import android.content.Context
import androidx.room.Room
import com.falabella.domain.usecase.GetEconomicIndicatorDetailUseCase
import com.falabella.domain.usecase.GetEconomicIndicatorListUseCase
import com.falabella.challenge.common.ConnectionHelper
import com.falabella.challenge.data.database.LocalDatabase


/**
 * Created by Anibal Cortez on 10/8/20.
 */
class AppContainer(private val appModule: AppModule, private val activity: Activity) {


    private fun getContext(): Context = activity

    private fun getLocalDataBase(): LocalDatabase =
        Room.databaseBuilder(
            activity.applicationContext,
            LocalDatabase::class.java, "falabella-db"
        ).build()

    private fun getConnectionHelper(): ConnectionHelper = ConnectionHelper(getContext())

    fun getEconomicIndicatorListUseCase() = GetEconomicIndicatorListUseCase(
        appModule.getRepository(
            getLocalDataBase(),
            getConnectionHelper()
        )
    )

    fun getEconomicIndicatorDetailUseCase() = GetEconomicIndicatorDetailUseCase(
        appModule.getRepository(
            getLocalDataBase(),
            getConnectionHelper()
        )
    )

}