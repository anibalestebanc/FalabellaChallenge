package com.falabella.falabellachallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falabella.falabellachallenge.data.database.economicindicator.EconomicIndicator
import com.falabella.falabellachallenge.data.database.economicindicator.EconomicIndicatorDao

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Database(entities = arrayOf(EconomicIndicator::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun economicIndicatorDao() : EconomicIndicatorDao
}