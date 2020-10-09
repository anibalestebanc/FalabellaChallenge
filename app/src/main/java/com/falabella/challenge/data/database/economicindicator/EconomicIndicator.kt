package com.falabella.challenge.data.database.economicindicator

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Entity
data class EconomicIndicator(
    @PrimaryKey val code: String,
    val name: String,
    val unitOfMeasure: String,
    val date: String,
    val value: String
)