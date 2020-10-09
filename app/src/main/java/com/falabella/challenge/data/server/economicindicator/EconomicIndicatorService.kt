package com.falabella.challenge.data.server.economicindicator

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface EconomicIndicatorService {

    @GET("/api")
    suspend fun getEconomicIndicatorList(): Response<EconomicIndicatorSchema>

    @GET("/api/{indicatorType}")
    suspend fun getEconomicIndicatorDetail(@Path("indicatorType") indicatorType : String) : Response<EconomicIndicatorDetailSchema>

}