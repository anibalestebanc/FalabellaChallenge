package com.falabella.challenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.falabella.challenge.data.database.entity.EconomicIndicatorDetail

/**
 * Created by Anibal Cortez on 10/8/20.
 */
@Dao
interface EconomicIndicatorDetailDao {

    @Query("SELECT * FROM EconomicIndicatorDetail WHERE code =:economicIndicatorCode")
    fun getEconomicIndicatorByCode(economicIndicatorCode : String): EconomicIndicatorDetail?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(economicIndicatorDetail: EconomicIndicatorDetail)
}