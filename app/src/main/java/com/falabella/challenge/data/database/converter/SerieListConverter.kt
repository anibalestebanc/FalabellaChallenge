package com.falabella.challenge.data.database.converter

import androidx.room.TypeConverter
import com.falabella.domain.model.Serie
import com.google.gson.Gson

/**
 * Created by Anibal Cortez on 10/8/20.
 */
object SerieListConverter {

    @TypeConverter
    @JvmStatic
    fun saveSerieList(serieList : List<Serie?>) : String{
        return Gson().toJson(serieList)
    }

    @TypeConverter
    @JvmStatic
    fun getSerieList(serieList : String) : List<Serie?>{
        return Gson().fromJson(serieList, Array<Serie>::class.java).toList()
    }
}