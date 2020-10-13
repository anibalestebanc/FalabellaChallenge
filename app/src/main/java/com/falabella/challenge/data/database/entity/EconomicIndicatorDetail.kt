package com.falabella.challenge.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.falabella.domain.model.Serie
import com.falabella.challenge.data.database.converter.SerieListConverter

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Entity
@TypeConverters(SerieListConverter::class)
data class EconomicIndicatorDetail (
    @PrimaryKey val code: String,
    val selieList : List<Serie>
)

