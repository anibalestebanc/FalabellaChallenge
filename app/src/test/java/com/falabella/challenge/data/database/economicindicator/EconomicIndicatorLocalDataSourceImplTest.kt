package com.falabella.challenge.data.database.economicindicator

import com.falabella.challenge.data.database.LocalDatabase
import com.nhaarman.mockitokotlin2.mock

/**
 * Created by Anibal Cortez on 10/8/20.
 */
class EconomicIndicatorLocalDataSourceImplTest {


    private val economicIndicatorDao: EconomicIndicatorDao = mock()
    private val localDataBase: LocalDatabase = mock()
    private val localDataSourceImpl = EconomicIndicatorLocalDataSourceImpl(localDataBase)

   /* @Test
    fun `If local database count is 0 then return economic indicator list is empty`() {
        runBlocking {

            whenever(localDataBase.economicIndicatorDao()).thenReturn(economicIndicatorDao)
            whenever(economicIndicatorDao.count()).thenReturn(0)

            val result = localDataSourceImpl.isEconomicIndicatorListEmpty()
            assertTrue(result)
        }
    }
    @Test
    fun validate(){ // if the local database count > 0 then there is elements in the list
       runBlocking {

           whenever(localDataBase.economicIndicatorDao()).thenReturn(economicIndicatorDao)
           whenever(economicIndicatorDao.count()).thenReturn(1)

           val result = localDataSourceImpl.isEconomicIndicatorListEmpty()
           assertFalse(result)
       }

    } */
}