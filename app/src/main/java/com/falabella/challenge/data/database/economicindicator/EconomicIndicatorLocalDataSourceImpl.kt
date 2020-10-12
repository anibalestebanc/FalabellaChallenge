package com.falabella.challenge.data.database.economicindicator

import com.falabella.data.source.EconomicIndicatorLocalDataSource
import com.falabella.domain.model.EconomicIndicator
import com.falabella.domain.model.EconomicIndicatorDetail
import com.falabella.challenge.data.database.LocalDatabase
import com.falabella.challenge.data.mapper.toDomain
import com.falabella.challenge.data.mapper.toRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EconomicIndicatorLocalDataSourceImpl(localDataBase: LocalDatabase)
    : EconomicIndicatorLocalDataSource {

    private val economicIndicatorDao = localDataBase.economicIndicatorDao()
    private val economicIndicatorDetailDao = localDataBase.economicIndicatorDetailDao()

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


    override suspend fun isEconomicIndicatorDetailEmpty(economicIndicatorCode: String): Boolean =
        withContext(Dispatchers.IO){
            if (economicIndicatorDetailDao.getEconomicIndicatorByCode(economicIndicatorCode) == null){
                return@withContext true
            }
            return@withContext false
    }

    override suspend fun getEconomicIndicatorDetail(economicIndicatorCode: String): EconomicIndicatorDetail =
        withContext(Dispatchers.IO){
        return@withContext economicIndicatorDetailDao.getEconomicIndicatorByCode(economicIndicatorCode)!!.toDomain()
    }

    override suspend fun saveEconomicIndicatorDetail(economicIndicatorDetail: EconomicIndicatorDetail) =
        withContext(Dispatchers.IO){
            economicIndicatorDetailDao.insert(economicIndicatorDetail.toRoom())
        }
}