package com.falabella.challenge.data.server

import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://mindicador.cl/"

    val economicIndicatorService: EconomicIndicatorService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(EconomicIndicatorService::class.java)
        }
}