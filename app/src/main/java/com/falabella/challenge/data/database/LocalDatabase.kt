package com.falabella.challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.falabella.challenge.data.database.entity.EconomicIndicator
import com.falabella.challenge.data.database.dao.EconomicIndicatorDao
import com.falabella.challenge.data.database.entity.EconomicIndicatorDetail
import com.falabella.challenge.data.database.dao.EconomicIndicatorDetailDao

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Database(entities = arrayOf(EconomicIndicator::class, EconomicIndicatorDetail::class), version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun economicIndicatorDao() : EconomicIndicatorDao
    abstract fun economicIndicatorDetailDao() : EconomicIndicatorDetailDao
}