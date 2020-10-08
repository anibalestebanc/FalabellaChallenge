package com.falabella.falabellachallenge.data.database.economicindicator

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.domain.model.EconomicIndicator
import com.falabella.falabellachallenge.data.database.LocalDatabase
import com.falabella.falabellachallenge.data.mapper.toDomain
import com.falabella.falabellachallenge.data.mapper.toRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EconomicIndicatorLocalDataSourceImpl(localDataBase: LocalDatabase)
    : EconomicIndicatorLocalDataSource {

    private val economicIndicatorDao = localDataBase.economicIndicatorDao()

    override suspend fun getEconomicIndicatorList(): List<EconomicIndicator> =
        withContext(Dispatchers.IO) {
            economicIndicatorDao.getEconomicIndicatorList().map { it.toDomain() }
        }

    override suspend fun isEconomicIndicatorListEmpty(): Boolean =
        withContext(Dispatchers.IO) {
            economicIndicatorDao.count() <= 0
    }

    override suspend fun saveEconomicIndicatorList(list: List<EconomicIndicator>) =
        withContext(Dispatchers.IO) {
            list.forEach { economicIndicatorDao.insert(it.toRoom()) }
        }
}