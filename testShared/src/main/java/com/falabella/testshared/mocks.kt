package com.falabella.testshared

import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.domain.model.Serie


val economicIndicatorMock = EconomicIndicator(
    code = "uf",
    name = "Unidad de fomento (UF)",
    unitOfMeasure = "Pesos",
    date = "2020-10-12T03:00:00.000Z",
    value = "28733.09"
)

val economicIndicatorListMock : List<EconomicIndicator> = listOf(economicIndicatorMock)

val sereMock = Serie(
    date = "2020-10-12T03:00:00.000Z",
    value = "28733.09"
)

val economicIndicatorDetailMock = EconomicIndicatorDetail(
    code = economicIndicatorMock.code,
    serieList = listOf(sereMock)
)

val serverErrorMock : Int = 500