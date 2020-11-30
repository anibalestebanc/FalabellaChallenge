package com.falabella.challenge.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.falabella.challenge.common.ConnectionHelper
import com.falabella.challenge.data.database.LocalDatabase
import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorLocalDataSourceImpl
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorRemoteDataSourceImpl
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorService
import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.data.source.EconomicIndicatorRemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {


    @Provides
    fun getContext(app: Application) : Context = app


    @Provides
    @Singleton
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(
            app,
            LocalDatabase::class.java,
            "falabella-db"
        ).build()


    @Provides
    @Singleton
    fun connectionHelperProviders(context: Context) =
        ConnectionHelper(context)


    @Provides
    fun dispacherProvider() : CoroutineDispatcher = Dispatchers.Main


    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider() = "https://mindicador.cl/"


    @Provides
    @Singleton
    fun getEconomicIndicatorServiceProvider(@Named("baseUrl") baseUrl: String): EconomicIndicatorService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .run {
                create(EconomicIndicatorService::class.java)
            }

    @Provides
    fun localDataSourceProvider(localDataBase: LocalDatabase) : EconomicIndicatorLocalDataSource =
        EconomicIndicatorLocalDataSourceImpl(localDataBase)

    @Provides
    fun remoteDataSourceProvider(
        connectionHelper: ConnectionHelper,
        economicIndicatorService: EconomicIndicatorService
    ) : EconomicIndicatorRemoteDataSource  =
        EconomicIndicatorRemoteDataSourceImpl(connectionHelper, economicIndicatorService)

}