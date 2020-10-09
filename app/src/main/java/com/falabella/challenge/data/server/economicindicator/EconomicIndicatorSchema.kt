package com.falabella.challenge.data.server.economicindicator

import com.google.gson.annotations.SerializedName

data class EconomicIndicatorSchema (
    @SerializedName("version")
    val version : String,
    @SerializedName("autor")
    val autor : String,
    @SerializedName("fecha")
    val fecha : String,
    @SerializedName("uf")
    val uf : Uf,
    @SerializedName("ivp")
    val ivp : Ivp,
    @SerializedName("dolar")
    val dolar : Dolar,
    @SerializedName("dolar_intercambio")
    val dolar_intercambio : Dolar_intercambio,
    @SerializedName("euro")
    val euro : Euro,
    @SerializedName("ipc")
    val ipc : Ipc,
    @SerializedName("utm")
    val utm : Utm,
    @SerializedName("imacec")
    val imacec : Imacec,
    @SerializedName("tpm")
    val tpm : Tpm,
    @SerializedName("libra_cobre")
    val libra_cobre : Libra_cobre,
    @SerializedName("tasa_desempleo")
    val tasa_desempleo : Tasa_desempleo,
    @SerializedName("bitcoin")
    val bitcoin : Bitcoin
)

data class Uf (
    @SerializedName("codigo")
    val codigo : String,
    @SerializedName("nombre")
    val nombre : String,
    @SerializedName("unidad_medida")
    val unidad_medida : String,
    @SerializedName("fecha")
    val fecha : String,
    @SerializedName("valor")
    val valor : Double
)

data class Ivp (
    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Dolar (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Dolar_intercambio (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Euro (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Ipc (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Utm (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Int
)

data class Imacec (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)


data class Tpm (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Libra_cobre (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)
data class Tasa_desempleo (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

data class Bitcoin (

    @SerializedName("codigo") val codigo : String,
    @SerializedName("nombre") val nombre : String,
    @SerializedName("unidad_medida") val unidad_medida : String,
    @SerializedName("fecha") val fecha : String,
    @SerializedName("valor") val valor : Double
)

