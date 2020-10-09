package com.falabella.challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falabella.challenge.data.database.economicindicator.EconomicIndicator
import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorDao
import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorDetail
import com.falabella.challenge.data.database.economicindicator.EconomicIndicatorDetailDao

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Database(entities = arrayOf(EconomicIndicator::class, EconomicIndicatorDetail::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun economicIndicatorDao() : EconomicIndicatorDao
    abstract fun economicIndicatorDetailDao() : EconomicIndicatorDetailDao
}