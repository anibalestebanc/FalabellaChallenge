package com.falabella.falabellachallenge.data.database.economicindicator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Dao
interface EconomicIndicatorDao {

    @Query("SELECT * FROM EconomicIndicator")
    fun getEconomicIndicatorList(): List<EconomicIndicator>

    @Query("SELECT COUNT() FROM EconomicIndicator")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(economicIndicator: EconomicIndicator)
}