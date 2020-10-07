package com.falabella.domain.model

data class EconomicIndicator(
    val code: String,
    val name: String,
    val unitOfMeasure: String,
    val date: String,
    val value: String
)
