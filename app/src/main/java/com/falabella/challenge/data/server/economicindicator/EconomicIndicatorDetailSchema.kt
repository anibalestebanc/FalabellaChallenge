package com.falabella.challenge.data.server.economicindicator

import com.google.gson.annotations.SerializedName

data class EconomicIndicatorDetailSchema (
    @SerializedName("version") val version : String,
    @SerializedName("autor") val autor : String,
    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("serie") val serie : List<Serie>
)

data class Serie (
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

