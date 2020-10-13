package com.falabella.challenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.falabella.challenge.data.database.entity.EconomicIndicator

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