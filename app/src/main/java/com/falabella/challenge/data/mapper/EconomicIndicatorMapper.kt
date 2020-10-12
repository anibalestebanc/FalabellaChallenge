package com.falabella.challenge.data.mapper

import com.falabella.domain.model.EconomicIndicator
import com.falabella.challenge.data.database.economicindicator.EconomicIndicator as EconomicIndicatorRoom
import com.falabella.challenge.data.server.economicindicator.EconomicIndicatorSchema


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

fun toDomain(code : String, name: String, unit : String, date: String, value : Double) : EconomicIndicator = EconomicIndicator(
    code = code,
    name = name,
    unitOfMeasure = unit,
    date = date,
    value = value.toString()
)

fun EconomicIndicatorSchema.toEconomicIndicatorList(): List<EconomicIndicator> =
    listOf(
        toDomain(uf.codigo,uf.nombre, uf.unidad_medida, uf.fecha,uf.valor),
        toDomain( ivp.codigo,ivp.nombre, ivp.unidad_medida, ivp.fecha,ivp.valor),
        toDomain( dolar.codigo,dolar.nombre, dolar.unidad_medida, dolar.fecha,dolar.valor),
        toDomain( dolar_intercambio.codigo,dolar_intercambio.nombre, dolar_intercambio.unidad_medida, dolar_intercambio.fecha,dolar_intercambio.valor),
        toDomain(euro.codigo,euro.nombre, euro.unidad_medida, euro.fecha,euro.valor),
        toDomain(ipc.codigo,ipc.nombre, ipc.unidad_medida, ipc.fecha,ipc.valor),
        toDomain(utm.codigo,utm.nombre, utm.unidad_medida, utm.fecha,utm.valor),
        toDomain(imacec.codigo,imacec.nombre, imacec.unidad_medida, imacec.fecha,imacec.valor),
        toDomain(tpm.codigo,tpm.nombre, tpm.unidad_medida, tpm.fecha,tpm.valor),
        toDomain(libra_cobre.codigo,libra_cobre.nombre, libra_cobre.unidad_medida, libra_cobre.fecha,libra_cobre.valor),
        toDomain(tasa_desempleo.codigo,tasa_desempleo.nombre, tasa_desempleo.unidad_medida, tasa_desempleo.fecha,tasa_desempleo.valor),
        toDomain(bitcoin.codigo,bitcoin.nombre, bitcoin.unidad_medida, bitcoin.fecha,bitcoin.valor)
    )
