package com.falabella.challenge.data.mapper

import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.model.Serie
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorDetailSchema
import com.falabella.challenge.data.server.economicindicator.Serie as SerieSchema

import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorDetail as EconomicIndicatorDetailRoom

/**
 * Created by Anibal Cortez on 10/8/20.
 */

fun EconomicIndicatorDetailRoom.toDomain() : EconomicIndicatorDetail = EconomicIndicatorDetail(
    code = code,
    serieList = selieList
)

fun  EconomicIndicatorDetail.toRoom() : EconomicIndicatorDetailRoom = EconomicIndicatorDetailRoom(
    code = code,
    selieList = serieList
)

fun EconomicIndicatorDetailSchema.toDomain() : EconomicIndicatorDetail = EconomicIndicatorDetail(
    code = codigo,
    serieList = serie.map { it.toDomain() }
)

fun SerieSchema.toDomain() : Serie = Serie(
    date = fecha,
    value = valor.toString()
)

