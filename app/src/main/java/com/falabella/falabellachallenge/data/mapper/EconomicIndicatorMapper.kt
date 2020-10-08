package com.falabella.falabellachallenge.data.mapper

import com.falabella.domain.model.EconomicIndicator
import com.falabella.falabellachallenge.data.database.economicindicator.EconomicIndicator as EconomicIndicatorRoom
import com.falabella.falabellachallenge.data.server.economicindicator.Bitcoin as BitcoinSchema
import com.falabella.falabellachallenge.data.server.economicindicator.EconomicIndicatorSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Tasa_desempleo as TasaDesempleoSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Libra_cobre as LibraCobreSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Tpm as TpmSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Imacec as ImacecSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Utm as UtmSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Ipc as IpcSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Euro as EuroSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Dolar_intercambio as DolarChangeSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Dolar as DolarSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Ivp as IvpSchema
import com.falabella.falabellachallenge.data.server.economicindicator.Uf as UfSchema


fun EconomicIndicatorRoom.toDomain() : EconomicIndicator = EconomicIndicator(
    code = code,
    name = name,
    unitOfMeasure = unitOfMeasure,
    date = date,
    value = value
)

fun  EconomicIndicator.toRoom() : EconomicIndicatorRoom = EconomicIndicatorRoom(
    code = code,
    name = name,
    unitOfMeasure = unitOfMeasure,
    date = date,
    value = value
)


fun EconomicIndicatorSchema.toEconomicIndicatorList(): List<EconomicIndicator> =
    listOf(
        uf.toDomain(),
        ivp.toDomain(),
        dolar.toDomain(),
        dolar_intercambio.toDomain(),
        euro.toDomain(),
        ipc.toDomain(),
        utm.toDomain(),
        imacec.toDomain(),
        tpm.toDomain(),
        libra_cobre.toDomain(),
        tasa_desempleo.toDomain(),
        bitcoin.toDomain()
    )


fun UfSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun IvpSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun DolarSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun DolarChangeSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun EuroSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun IpcSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun UtmSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun ImacecSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun TpmSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun LibraCobreSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun TasaDesempleoSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)

fun BitcoinSchema.toDomain(): EconomicIndicator = EconomicIndicator(
    code = codigo,
    name = nombre,
    unitOfMeasure = unidad_medida,
    date = fecha,
    value = valor.toString()
)